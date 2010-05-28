package com.ast.kinoshka.frontend.service;

import com.ast.kinoshka.backend.service.ImageService;
import com.google.inject.Inject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ImageUploadServlet extends BaseServiceServlet {
  public static final String ACTION = "uploadService";

  @Inject
  private ImageService service;

  @SuppressWarnings("unchecked")
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    if (!ServletFileUpload.isMultipartContent(request))
      return;

    FileItemFactory factory = new DiskFileItemFactory();
    ServletFileUpload upload = new ServletFileUpload(factory);
    List items = null;

    try {
      items = upload.parseRequest(request);
    } catch (FileUploadException e) {
      e.printStackTrace();
      return;
    }
    for (Iterator i = items.iterator(); i.hasNext();) {
      FileItem item = (FileItem) i.next();
      if (item.isFormField())
        continue;

      String result = service.saveTmpImage(item.get(), item.getName());
      response.getWriter().write("tmpfile:" + result);

    } // end for
  }

}

