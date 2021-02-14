using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace pi_lab05b.Controllers
{
    [RoutePrefix("it")]
    public class MResearchController : Controller
    {
        [HttpGet]
        [Route("{n:int}/{word}")]
        public string M01B(int n, string word)
        {
            return $"GET:M01:/{ n }/" + word;
        }

        [AcceptVerbs("get", "post")]
        [Route("{b:bool}/{letters:regex(^[\\p{L}]+$)}")]
        public string M02(bool b, string letters)
        {
            return $"{HttpContext.Request.HttpMethod}:M02:/{ b }/" + letters;
        }

        [AcceptVerbs("get", "delete")]
        [Route("{f:float}/{word:length(2, 5)}")]
        public string M03(float f, string word)
        {
            return $"{HttpContext.Request.HttpMethod}:M03:/{ f }/" + word;
        }

        [HttpPut]
        [Route("{letters:regex(^[\\p{L}]{3,4}$)}/{n:range(100, 200)}")]
        public string M04(string letters, int n)
        {
            return $"PUT:M04:/{ letters }/" + n;
        }

        [HttpPost]
        [Route(@"{mail:regex(^(.*)@(.*)\.(.*)$)}")]
        public string M05(string mail)
        {
            return "POST:M05:" + mail;
        }
    }
}   