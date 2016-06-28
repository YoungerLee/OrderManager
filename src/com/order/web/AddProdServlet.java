package com.order.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.order.domain.Product;
import com.order.domain.User;
import com.order.factory.BasicFactory;
import com.order.service.ProdService;
import com.order.util.IOUtils;
import com.order.util.PicUtils;

public class AddProdServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter resp_out = response.getWriter();
		ProdService service = BasicFactory.getFactory().getService(
				ProdService.class);
		String encode = this.getServletContext().getInitParameter("encode");
		Map<String, String> paramMap = new HashMap<String, String>();
		try {
			// 1.上传图片
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置内存缓冲区大小为100k
			factory.setSizeThreshold(1024 * 100);
			// 设置临时文件夹的位置
			factory.setRepository(new File(this.getServletContext()
					.getRealPath("WEB-INF/temp")));
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			fileUpload.setHeaderEncoding(encode);
			// 设置单个文件最大大小
			fileUpload.setFileSizeMax(1024 * 1024 * 10);
			fileUpload.setSizeMax(1024 * 1024 * 100);

			if (!fileUpload.isMultipartContent(request)) {
				throw new RuntimeException("请使用正确的表单进行上传!");
			}

			@SuppressWarnings("unchecked")
			List<FileItem> list = fileUpload.parseRequest(request);
			for (FileItem item : list) {
				if (item.isFormField()) {
					// 普通字段
					String name = item.getFieldName();
					String value = item.getString(encode);
					paramMap.put(name, value);
				} else {
					// 文件上传项
					String fileNameIncPath = item.getName();// 获取的文件名包含路径
					// 非空检验
					if (fileNameIncPath == null || "".equals(fileNameIncPath)) {
						resp_out.println("<script language='javascript'>alert('必须选择一张图片！');window.location='"
								+ request.getContextPath()
								+ "/jsp/addDev.jsp'</script>");
						return;
					} else {
						// 去除路径取出真实文件名
						String realname = fileNameIncPath
								.substring(fileNameIncPath.lastIndexOf("\\") + 1);
						String uuidname = UUID.randomUUID().toString() + "_"
								+ realname;
						String hash = Integer.toHexString(uuidname.hashCode());
						String upload = this.getServletContext().getRealPath(
								"WEB-INF/upload");
						String imgurl = "/WEB-INF/upload";
						for (char c : hash.toCharArray()) {
							upload += "/" + c;
							imgurl += "/" + c;
						}
						imgurl += "/" + uuidname;
						paramMap.put("imgurl", imgurl);

						File uploadFile = new File(upload);
						if (!uploadFile.exists())
							uploadFile.mkdirs();

						InputStream in = item.getInputStream();
						OutputStream out = new FileOutputStream(new File(
								upload, uuidname));

						IOUtils.In2Out(in, out);
						IOUtils.close(in, out);

						item.delete();

						// --生成缩略图
						PicUtils picu = new PicUtils(this.getServletContext()
								.getRealPath(imgurl));
						picu.resizeByHeight(140);
					}
				}
			}
			// 2.调用Service中提供的方法,在数据库中添加商品
			User admin = (User) request.getSession().getAttribute("user");
			Product prod = new Product();
			BeanUtils.populate(prod, paramMap);
			prod.setAdmin_id(admin.getId());
			service.addProd(prod);

			// 3.提示成功,回到主页
			resp_out.println("<script language='javascript'>alert('添加成功！');window.location='"
					+ request.getContextPath() + "/admin.jsp'</script>");

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}