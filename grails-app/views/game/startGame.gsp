<%--
  Created by IntelliJ IDEA.
  User: sgates
  Date: 3/16/13
  Time: 11:19 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Remnants of Revenants</title>
    <meta name="layout" content="game"/>
    <style>
        /* CUSTOMIZE THE NAVBAR
-------------------------------------------------- */

        /* Special class on .container surrounding .navbar, used for positioning it into place. */
    .navbar-wrapper {
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        z-index: 10;
        margin-top: 20px;
        margin-bottom: -90px; /* Negative margin to pull up carousel. 90px is roughly margins and height of navbar. */
    }

    .navbar-wrapper .navbar {

    }

        /* Remove border and change up box shadow for more contrast */
    .navbar .navbar-inner {
        border: 0;
        -webkit-box-shadow: 0 2px 10px rgba(0, 0, 0, .25);
        -moz-box-shadow: 0 2px 10px rgba(0, 0, 0, .25);
        box-shadow: 0 2px 10px rgba(0, 0, 0, .25);
    }

        /* Downsize the brand/project name a bit */
    .navbar .brand {
        padding: 14px 20px 16px; /* Increase vertical padding to match navbar links */
        font-size: 16px;
        font-weight: bold;
        text-shadow: 0 -1px 0 rgba(0, 0, 0, .5);
    }

        /* Navbar links: increase padding for taller navbar */
    .navbar .nav > li > a {
        padding: 15px 20px;
    }

        /* Offset the responsive button for proper vertical alignment */
    .navbar .btn-navbar {
        margin-top: 10px;
    }

    </style>
</head>

<body>
<g:render template="navBar"/>
<div class="container" style="margin-top: 75px;">
    <div id="playField" class="well" style="min-height: 250px; max-height: 250px; overflow:auto;">
        Welcome.
    </div>

    <div class="container">
        <g:textArea class="container" name="textInput" id="inputField" style="background-color: black;"/>
    </div>
</div>

<r:script>
    jQuery('#inputField').bind('keypress', function (e) {
        switch (e.which) {
            case 13:
                postBack(); //return
        }
    })

    function postBack() {
        jQuery.post("/Remnants/game/clientAction", { actionText: jQuery('#inputField').val() }, function(response){
            var theDiv = jQuery('#playField')
            var currentText = theDiv.html()
            jQuery('#playField').html(currentText + response);
            theDiv.animate({ scrollTop: theDiv.prop("scrollHeight") - theDiv.height() }, 500);
        })
        .done(function () {
           console.log("Action posted");
           jQuery('#inputField').val("");
        }).fail(function () {
            alert("Action failed!");
        });
    }
</r:script>
</body>
</html>