using System.Web;

namespace pi_lab05b.Handlers
{
    public class AResearchHandler : IHttpHandler
    {
        public bool IsReusable => true;

        public void ProcessRequest(HttpContext context)
        {
            context.Response.AddHeader("AResearchHandler", "hello from handler");
        }
    }
}