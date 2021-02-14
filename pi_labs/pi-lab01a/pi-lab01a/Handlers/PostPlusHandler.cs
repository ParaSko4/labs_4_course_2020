using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using System.Text.Json;
using System.Diagnostics;

namespace pi_lab01a.Handlers
{
    public class PostPlusHandler : IHttpHandler
    {
        class ReciveData
        {
            public int x { get; set; }
            public int y { get; set; }
        }

        public void ProcessRequest(HttpContext context)
        {
            context.Response.ContentType = "text/plain";
            var rd = JsonSerializer.Deserialize<ReciveData>(new StreamReader(context.Request.InputStream, context.Request.ContentEncoding).ReadToEnd());
            context.Response.Write(rd.x + rd.y);
        }

        public bool IsReusable
        {
            get { return true; }
        }
    }
}