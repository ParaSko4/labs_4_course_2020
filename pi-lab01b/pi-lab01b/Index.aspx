<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Index.aspx.cs" Inherits="pi_lab01b.Index" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<body>
    <script src="modernizr.js"></script>
    <script>
        var ta;
        var ws = null;
        var bstart;
        var bstop;

        window.onload = function () {
            if (Modernizr.websockets) {
                WriteMessage('support', 'yes');
                ta = document.getElementById('ta');
                bstart = document.getElementById('bstart');
                bstop = document.getElementById('bstop');
                bstart.disabled = false;
                bstop.disabled = true;
            }
        }

        function WriteMessage(idspan, txt) {
            var span = document.getElementById(idspan);
            span.innerHTML = txt;
        }

        function exe_start() {
            if (ws == null) {
                ws = new WebSocket('ws://localhost:10222/1b/Websokets.websocket');
                ws.onopen = function () { ws.send('Connection...'); }
                ws.onclose = function (s) { console.log('onclose', s); }
                ws.onmessage = function (evt) { ta.innerHTML += '\n' + evt.data; }
                bstart.disabled = true;
                bstop.disabled = false;
            }
        }

        function exe_stop() {
            ws.close(3001, 'stopapplication');
            ws = null;
            bstart.disabled = false;
            bstop.disabled = true;
        }
    </script>

    <section style="width: 800px; float:left;">
        <div id="wsproperties">
            Web Sockets supports?:
            <span id="support"></span><br />
        </div>
    </section>
    <br />
    <section style="width: 215px; border:solid;">
        <div style="margin: 5px 5px 5px 5px;">
            <input id="bstart" type="button" value="Start"
                onclick="exe_start()" style="width: 100px" />
            
        <input id="bstop" type="button" value="Stop"
            onclick="exe_stop()" style="width: 100px" />
            <textarea id="ta" rows="20" cols="25"
                style="text-align:center;" ></textarea>
        </div>
    </section>
</body>
</html>
