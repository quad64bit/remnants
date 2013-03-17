<!-- NAVBAR
    ================================================== -->
<div class="navbar-wrapper">
    <!-- Wrap the .navbar in .container to center it within the absolutely positioned parent. -->
    <div class="container">

        <div class="navbar navbar-inverse">
            <div class="navbar-inner">
                <!-- Responsive Navbar Part 1: Button for triggering responsive navbar (not covered in tutorial). Include responsive CSS to utilize. -->
                <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="brand" href="${g.createLink(action:'index')}">Remnants of Revenants</a>
                <!-- Responsive Navbar Part 2: Place all navbar contents you want collapsed withing .navbar-collapse.collapse. -->
                <div class="nav-collapse collapse">
                    <ul class="nav">
                        <li class="active"><a href="#">Home</a></li>
                        <li><a href="#about">About</a></li>
                        <li><a href="#contact">Contact</a></li>
                        <!-- Read about Bootstrap dropdowns at http://twitter.github.com/bootstrap/javascript.html#dropdowns -->
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Account<b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Login</a></li>
                                <li><a href="#">Create Account</a></li>
                                <li><a href="#">My Account</a></li>
                                <li><a href="#">Sign Out</a></li>
                            </ul>
                        </li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div><!-- /.navbar-inner -->
        </div><!-- /.navbar -->

    </div> <!-- /.container -->
</div><!-- /.navbar-wrapper -->

