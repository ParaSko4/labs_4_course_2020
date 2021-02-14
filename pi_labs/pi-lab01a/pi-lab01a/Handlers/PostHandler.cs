using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace pi_lab01a.Handlers
{
    public class PostHandler : IHttpHandler
    {
        public void ProcessRequest(HttpContext context)
        {
            HttpRequest rq = context.Request;
            HttpResponse rs = context.Response;

            rs.ContentType = "text/plain";
            rs.Write($"POST-Http-SHV:ParamA = {rq.Form["ParamA"]},ParamB = {rq["ParamB"]}");
        }

        public bool IsReusable
        {
            get { return false; }
        }
    }
}