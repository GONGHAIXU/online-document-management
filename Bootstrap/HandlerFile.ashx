using System;
using System.Web;
using System.Text;

public class HandlerFile : IHttpHandler {
    
    public void ProcessRequest (HttpContext context) {
        context.Response.ContentType = "application/json";
        HttpPostedFile uploadFile = context.Request.Files[0];

        string fileName = uploadFile.FileName;
        int fileSize = uploadFile.ContentLength;
        string fileExt = System.IO.Path.GetExtension(fileName).ToLower();
        string directoryPath = "upload/";
        uploadFile.SaveAs(System.Web.HttpContext.Current.Server.MapPath(directoryPath) + fileName); 
        context.Response.Write("{\"fileName\":"+"\""+fileName+"\"}");
    }
 
    public bool IsReusable {
        get {
            return false;
        }
    }
}