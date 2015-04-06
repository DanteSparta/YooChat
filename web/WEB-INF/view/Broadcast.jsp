<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Broadcast Page</title>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/style/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath}/style/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="${pageContext.request.contextPath}/style/timeline.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/style/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath}/style/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="application/javascript">

        var Chat = {};

        Chat.socket = null;

        Chat.connect = (function (host) {
            if ('WebSocket' in window) {
                Chat.socket = new WebSocket(host);
            } else if ('MozWebSocket' in window) {
                Chat.socket = new MozWebSocket(host);
            } else {
                Console.log('Error: WebSocket is not supported by this browser.');
                return;
            }

            Chat.socket.onopen = function () {
                Console.log('Info: WebSocket connection opened.');
                document.getElementById('chat').onkeydown = function (event) {
                    if (event.keyCode == 13) {
                        Chat.sendMessage();
                    }
                };
            };

            Chat.socket.onclose = function () {
                document.getElementById('chat').onkeydown = null;
                Console.log('Info: WebSocket closed.');
            };

            Chat.socket.onmessage = function (message) {
                Console.log(message.data);
            };
        });

        Chat.initialize = function () {
            if (window.location.protocol == 'http:') {
                Chat.connect('ws://' + window.location.host + '/broadcast');
            } else {
                Chat.connect('wss://' + window.location.host + '/broadcast');
            }

            localStorage.setItem("Count", 2);
        };

        Chat.sendMessage = (function () {
            var message = document.getElementById('chat').value;
            if (message != '') {
                Chat.socket.send(message);
                document.getElementById('chat').value = '';
            }
        });

        var Console = {};

        Console.log = (function (message) {
            var console = document.getElementById('console');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.innerHTML = message;
            console.appendChild(p);
            while (console.childNodes.length > 25) {
                console.removeChild(console.firstChild);
            }
            console.scrollTop = console.scrollHeight;
        });

        Chat.initialize();


        document.addEventListener("DOMContentLoaded", function () {
            // Remove elements with "noscript" class - <noscript> is not allowed in XHTML
            var noscripts = document.getElementsByClassName("noscript");
            for (var i = 0; i < noscripts.length; i++) {
                noscripts[i].parentNode.removeChild(noscripts[i]);
            }
        }, false)

    </script>
</head>

<body>

<div class="col-lg-12">
    <div class="col-lg-8" style="margin-left: 17.5%;">
        <div class="chat-panel panel panel-default">
            <div class="panel-heading">
                Content &nbsp;&nbsp;&nbsp;
                <%
                    Integer count = (Integer) application.getAttribute("count");
                    if (count >= 0) {
                %>
                <a id="chatRoom" href="${pageContext.request.contextPath}/yoo">Goto chatRoom</a>
                <%
                    }
                %>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div id="console"/>
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel-footer -->
        <div class="panel-footer">
            <div class="input-group">
                <input id="chat" type="text" class="form-control input-sm" placeholder="Type your message here...">
                                <span class="input-group-btn">
                                    <button class="btn btn-info btn-sm" id="btn-chat">
                                        Send
                                    </button>
                                </span>
            </div>
        </div>
    </div>
    <!-- /.panel -->
</div>
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/script/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath}/script/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="${pageContext.request.contextPath}/script/metisMenu.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="${pageContext.request.contextPath}/script/raphael-min.js"></script>
<script src="${pageContext.request.contextPath}/script/morris.min.js"></script>
<script src="${pageContext.request.contextPath}/script/morris-data.js"></script>

<!-- Custom Theme JavaScript -->
<script src="${pageContext.request.contextPath}/script/sb-admin-2.js"></script>
</body>

</html>
