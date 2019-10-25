<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <script>
        function send(usver, text) {
            let body = {
                usver: usver,
                text: text
            };
            $.ajax({
                url: "/mes",
                method: "POST",
                data: JSON.stringify(body),
                contentType: "application/json",
                dataType: "json",
                complete: function () {
                }
            });
        }
        function receiveMessage(usver) {
            $.ajax({
                url: "/mes?usver=" + usver,
                method: "GET",
                dataType: "json",
                contentType: "application/json",
                success: function (response) {
                    $('#messages').first().after("<li>" + response[0]["usver"] + "::" + response[0]["text"] + "</li>");
                    receiveMessage(usver);
                }
            })
        }
        function login(usver) {
            let body = {
                usver: usver,
                text: 'H3LL0, MEGA' + usver
            };
            $.ajax({
                url: "/mes",
                method: "POST",
                data: JSON.stringify(body),
                contentType: "application/json",
                dataType: "json",
                complete: function () {
                    receiveMessage(usver);
                }
            });
        }
    </script>
</head>
<body onload="login('${usver}')">
<h1>CHAT</h1>
<h3>HELLO, ${usver}</h3>
<label>MESSAGE</label>
<br>
<textarea type="text" name="message" id="mes" style="width: 50%; height: 5%"></textarea>
<br>
<button onclick="send('${usver}', $('#mes').val())">PUT MESSAGE</button>
<br>
<label><h2>CHAT</h2></label>
<ul id="messages">
    <#if messages??>
        <#list messages as messag>
            <li>${messag.usver.usvername}::${messag.message}  </li>
        </#list>
    </#if>
</ul>
</body>

</html>