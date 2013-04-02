<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Remnants of Revenants</title>
<meta name="description" content="">
<meta name="author" content="">
<meta name="layout" content="game"/>

<style>
    /* GLOBAL STYLES
    -------------------------------------------------- */
    /* Padding below the footer and lighter body text */

body {
    padding-bottom: 40px;
    color: #5a5a5a;
}

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

    /* CUSTOMIZE THE CAROUSEL
    -------------------------------------------------- */

    /* Carousel base class */
.carousel {
    margin-bottom: 60px;
}

.carousel .container {
    position: relative;
    z-index: 9;
}

.carousel-control {
    height: 80px;
    margin-top: 0;
    font-size: 120px;
    text-shadow: 0 1px 1px rgba(0, 0, 0, .4);
    background-color: transparent;
    border: 0;
    z-index: 10;
}

.carousel .item {
    height: 500px;
}

.carousel img {
    position: absolute;
    top: 0;
    left: 0;
    min-width: 100%;
    height: 500px;
}

.carousel-cap {
    background-color: transparent;
    position: static;
    max-width: 550px;
    padding: 0 20px;
    margin-top: 200px;
}

.carousel-cap h1,
.carousel-cap .lead {
    margin: 0;
    line-height: 1.25;
    color: #fff;
    text-shadow: 0 1px 1px rgba(0, 0, 0, .4);
}

.carousel-cap .btn {
    margin-top: 10px;
}

    /* MARKETING CONTENT
    -------------------------------------------------- */

    /* Center align the text within the three columns below the carousel */
.marketing .span4 {
    text-align: center;
}

.marketing h2 {
    font-weight: normal;
}

.marketing .span4 p {
    margin-left: 10px;
    margin-right: 10px;
}

    /* Featurettes
    ------------------------- */

.featurette-divider {
    margin: 80px 0; /* Space out the Bootstrap <hr> more */
}

.featurette {
    padding-top: 120px; /* Vertically center images part 1: add padding above and below text. */
    overflow: hidden; /* Vertically center images part 2: clear their floats. */
}

.featurette-image {
    margin-top: -120px; /* Vertically center images part 3: negative margin up the image the same amount of the padding to center it. */
}

    /* Give some space on the sides of the floated elements so text doesn't run right into it. */
.featurette-image.pull-left {
    margin-right: 40px;
}

.featurette-image.pull-right {
    margin-left: 40px;
}

    /* Thin out the marketing headings */
.featurette-heading {
    font-size: 50px;
    font-weight: 300;
    line-height: 1;
    letter-spacing: -1px;
}

    /* RESPONSIVE CSS
    -------------------------------------------------- */

@media (max-width: 979px) {

    .container.navbar-wrapper {
        margin-bottom: 0;
        width: auto;
    }

    .navbar-inner {
        border-radius: 0;
        margin: -20px 0;
    }

    .carousel .item {
        height: 500px;
    }

    .carousel img {
        width: auto;
        height: 500px;
    }

    .featurette {
        height: auto;
        padding: 0;
    }

    .featurette-image.pull-left,
    .featurette-image.pull-right {
        display: block;
        float: none;
        max-width: 40%;
        margin: 0 auto 20px;
    }
}

@media (max-width: 767px) {

    .navbar-inner {
        margin: -20px;
    }

    .carousel {
        margin-left: -20px;
        margin-right: -20px;
    }

    .carousel .container {

    }

    .carousel .item {
        height: 300px;
    }

    .carousel img {
        height: 300px;
    }

    .carousel-cap {
        width: 65%;
        padding: 0 70px;
        margin-top: 100px;
    }

    .carousel-cap h1 {
        font-size: 30px;
    }

    .carousel-cap .lead,
    .carousel-cap .btn {
        font-size: 18px;
    }

    .marketing .span4 + .span4 {
        margin-top: 40px;
    }

    .featurette-heading {
        font-size: 30px;
    }

    .featurette .lead {
        font-size: 18px;
        line-height: 1.5;
    }
}
</style>
</head>

<body>

<g:render template="navBar"/>

<!-- Carousel
    ================================================== -->
<div id="myCarousel" class="carousel slide">
    <div class="carousel-inner">
        <div class="item active">
            <img src="${resource(dir: 'images', file: 'slide-01.jpg')}" alt="Slide 1"/>
            <div class="container">
                <div class="carousel-cap">
                    <h1>A Grim Tale</h1>

                    <p class="lead">Journey deep into the forgotten depths...</p>
                    <a class="btn btn-large btn-inverse" href="${g.createLink(action:'startGame')}">Begin</a>
                </div>
            </div>
        </div>

        <div class="item">
            <img src="${resource(dir: 'images', file: 'slide-02.jpg')}" alt="Slide 2"/>

            <div class="container">
                <div class="carousel-cap">
                    <h1>The macabre account of one man...</h1>

                    <p class="lead">He discovered what was meant for no man</p>
                    <a class="btn btn-large btn-inverse" href="#">Learn more</a>
                </div>
            </div>
        </div>

        <div class="item">
            <img src="${resource(dir: 'images', file: 'slide-03.jpg')}" alt="Slide 3"/>

            <div class="container">
                <div class="carousel-cap">
                    <h1>"I have seen beyond the bounds of infinity..."</h1>

                    <p class="lead">...I have harnessed the shadows that stride from world to world to sow death and madness...</p>
                    <a class="btn btn-large btn-inverse" href="#">Browse gallery</a>
                </div>
            </div>
        </div>
    </div>
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
</div><!-- /.carousel -->



<!-- Marketing messaging and featurettes
    ================================================== -->
<!-- Wrap the rest of the page in another container to center all the content. -->

<div class="container marketing">

    <!-- Three columns of text below the carousel -->
    <div class="row">
        <div class="span4">
            <img class="img" src="${resource(dir: 'images', file: 'old_book.png')}">

            <h2>Knowledge</h2>

            <p>“The most merciful thing in the world, I think, is the inability of the human mind to correlate all its contents..."</p>

            <p><a class="btn" href="#">View details &raquo;</a></p>
        </div><!-- /.span4 -->
        <div class="span4">
            <img class="img" src="${resource(dir: 'images', file: 'skull.png')}">

            <h2>Arcane</h2>

            <p>“Now connect the cord of the lens machine with the upper socket on the cylinder — there! "</p>

            <p><a class="btn" href="#">View details &raquo;</a></p>
        </div><!-- /.span4 -->
        <div class="span4">
            <img class="img" src="${resource(dir: 'images', file: 'lock.png')}">

            <h2>Secrets</h2>

            <p>"To this day I do not know why I obeyed those whispers so slavishly, or whether I thought Akeley was mad or sane."</p>

            <p><a class="btn" href="#">View details &raquo;</a></p>
        </div><!-- /.span4 -->
    </div><!-- /.row -->


<!-- START THE FEATURETTES -->

    <hr class="featurette-divider">

    <div class="featurette">
        <img class="featurette-image pull-right" src="${resource(dir: 'images', file: 'cthulhu-walks.png')}">

        <h2 class="featurette-heading">First featurette headling. <span class="muted">It'll blow your mind.</span></h2>

        <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
    </div>

    <hr class="featurette-divider">

    <div class="featurette">
        <img class="featurette-image pull-left" src="${resource(dir: 'images', file: 'science2.png')}">

        <h2 class="featurette-heading">Oh yeah, it's that good. <span class="muted">See for yourself.</span></h2>

        <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
    </div>

    <hr class="featurette-divider">

    <div class="featurette">
        <img class="featurette-image pull-right" src="${resource(dir: 'images', file: 'hospital.png')}">

        <h2 class="featurette-heading">And lastly, this one. <span class="muted">Checkmate.</span></h2>

        <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
    </div>

    <hr class="featurette-divider">

    <!-- /END THE FEATURETTES -->


    <!-- FOOTER -->
    <footer>
        <p class="pull-right"><a href="#">Back to top</a></p>

        <p>&copy; 2013 Company, Inc. &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a></p>
    </footer>

</div><!-- /.container -->

<script>
    !function ($) {
        $(function () {
            $('#myCarousel').carousel()
        })
    }(window.jQuery)
</script>
</body>
</html>
