using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Web.Http;
using System.Web.Http.Description;
using System.Web.UI.WebControls;
using pi_lab07a.Context;
using pi_lab07a.Models;

namespace pi_lab07a.Controllers
{
    public class ContactsController : ApiController
    {
        private ContactContext db = new ContactContext();

        [HttpGet]
        [Route("GET/TS")]
        public IQueryable<Contacts> Getcontacts()
        {
            return db.contacts;
        }

        [HttpGet]
        [Route("GET/TS/{id}")]
        [ResponseType(typeof(Contacts))]
        public IHttpActionResult GetContacts(int id)
        {
            Contacts contacts = db.contacts.Find(id);
            if (contacts == null)
            {
                return NotFound();
            }

            return Ok(contacts);
        }

        [HttpPut]
        [Route("PUT/TS/{id}")]
        [ResponseType(typeof(void))]
        public IHttpActionResult PutContacts(int id, Contacts contacts)
        {
            contacts.id = id;
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (!ContactsExists(id))
            {
                return BadRequest();
            }

            db.Entry(contacts).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ContactsExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        [HttpPost]
        [Route("POST/TS")]
        [ResponseType(typeof(Contacts))]
        public IHttpActionResult PostContacts(Contacts contacts)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.contacts.Add(contacts);
            db.SaveChanges();

            return Ok(contacts);
        }

        [HttpDelete]
        [Route("DELETE/TS/{id}")]
        [ResponseType(typeof(Contacts))]
        public IHttpActionResult DeleteContacts(int id)
        {
            Contacts contacts = db.contacts.Find(id);
            if (contacts == null)
            {
                return NotFound();
            }

            db.contacts.Remove(contacts);
            db.SaveChanges();

            return Ok(contacts);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool ContactsExists(int id)
        {
            return db.contacts.Count(e => e.id == id) > 0;
        }
    }
}