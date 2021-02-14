using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Web;

namespace pi_lab01a.Handlers
{
    public class GetPostHandler : IHttpHandler
    {
        public bool IsReusable => true;

        public void ProcessRequest(HttpContext context)
        {
            HttpRequest rq = context.Request;
            HttpResponse rs = context.Response;

            switch (rq.HttpMethod)
            {
                case "GET":
                    using (FileStream fstream = File.OpenRead(@"C:\Users\Mages\source\repos\pi-lab01a\pi-lab01a\VIew\page.html"))
                    {
                        byte[] array = new byte[fstream.Length];
                        fstream.Read(array, 0, array.Length);
                        rs.Write(Encoding.Default.GetString(array));
                    }
                    break;
                case "POST":
                    if (rq["x"] != null && rq["y"] != null)
                    {
                        rs.Write($"result : {Int32.Parse(rq["x"]) * Int32.Parse(rq["y"])}");
                    }
                    else
                    {
                        rs.Write("result form : " + Int32.Parse(rq.Form["x"]) * Int32.Parse(rq.Form["y"]));
                    }
                    break;
            }
        }
    }
}