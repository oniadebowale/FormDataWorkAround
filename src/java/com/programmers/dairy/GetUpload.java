/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.programmers.dairy;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class GetUpload extends HttpServlet {

    private static final long serialVersionUID = 9072176907951390388L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("##processRequest() : Enter");
        HttpSession session = request.getSession(true);
        PrintWriter out = response.getWriter();

        boolean IsIE = true;
        if ((null != request.getHeader("Accept")) && (request.getHeader("Accept").contains("application/json"))) {
            IsIE = false;
        }

        String strContentType = !IsIE ? "application/json" : "text/plain";
        String strCharacterEncode = "UTF-8";

        response.setContentType(strContentType);
        response.setCharacterEncoding(strCharacterEncode);
        System.out.println("##processRequest() : set ContentType to [" + strContentType + "] and CharacterEncoding to [" + strCharacterEncode + "]");

        FileItem item = null;
        try {
            String strFileName = "";
            String strFilePath = "";

            if (!ServletFileUpload.isMultipartContent(request)) {
                System.out.println("##processRequest() : request does not content MultipartContent");
                return;
            }
            System.out.println("##processRequest() : request contents MultipartContent");

            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(3145728);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);

            upload.setFileSizeMax(41943040L);
            upload.setSizeMax(52428800L);

            //let create a directory for proper tidiness of our files 
            String strUploadPath = getServletContext().getRealPath("") + File.separator + "upload";

            File uploadDir = new File(strUploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            System.out.println("##processRequest() : prepared directory to be written");
            List formItems = upload.parseRequest(request);
            Iterator iter = formItems.iterator();

            while (iter.hasNext()) {
                item = (FileItem) iter.next();

                if (!item.isFormField()) {
                    strFileName = new File(item.getName()).getName();
                    strFilePath = strUploadPath + File.separator + strFileName;
                    File storeFile = new File(strFilePath);
                    
                    //let now write file to directory
                    item.write(storeFile);
                }

                System.out.println("##processRequest() : File Path : [" + strFilePath + "]");
                String fileName = new File(strFilePath).getName();
                System.out.println("##processRequest() : File Name : [" + fileName + "]");
                out.print("Upload Successful");
            }
        } catch (Exception t) {
            System.err.println("##processRequest() : Failed Upload" + t.getMessage());
            return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("##doGet() : Enter");
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("##doPost() : Enter");
        processRequest(request, response);
    }

}
