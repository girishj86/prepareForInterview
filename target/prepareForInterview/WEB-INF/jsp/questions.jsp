<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Questions</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- bootstrap 3.0.2 -->
        <link href="/prepareForInterview/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="/prepareForInterview/resources/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="/prepareForInterview/resources/css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="/prepareForInterview/resources/css/AdminLTE.css" rel="stylesheet" type="text/css" />
        <!-- iCheck for checkboxes and radio inputs -->
        <link href="/prepareForInterview/resources/css/iCheck/all.css" rel="stylesheet" type="text/css" />

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

    </head>
    <body class="skin-blue">
   
         <div class="wrapper row-offcanvas row-offcanvas-left">
           
             <!-- Main content -->
                <section class="content" style="margin-top:0%;margin-left:8%;">
                
                 <div class="row" style="margin-bottom:1%">
                <div class="col-md-11">
                      <div class="box-tools pull-left">
                        <div class="checkbox">
                            <input type="checkbox"/>&nbsp;Show only Important
                        </div>
                    </div>
                </div><!-- /.col -->
            </div>

            <div class="row" style="margin-bottom:1%">
                <div class="col-md-11">
                      <div class="box-tools pull-left">
                    <button type="button" class="btn btn-success btn-flat"><i class="fa fa-angle-double-left"></i> previous</button>
                    </div>
                   
                    <div class="btn bg-navy" style="cursor:none;margin-left:35%;margin-right:35%">
                       <font size="4%"><b>${topicId}</b></font>
                    </div>
                   
                    <div class="box-tools pull-right">
                   <div class="btn-group">
                                            
                                            <button type="button" class="btn btn-default btn-flat dropdown-toggle" data-toggle="dropdown">
                                                <span class="caret"></span>
                                                <span class="sr-only">Toggle Dropdown</span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li>&nbsp;<input type="checkbox"/><font color="black">&nbsp;Mark as Important</font></li>
                                            </ul>
                                            <button type="button" class="btn btn-success btn-flat">next <i class="fa fa-angle-double-right"></i></button>
                    </div>
                    </div>
                        
                   
                </div><!-- /.col -->
            </div>
                    
                    <div class="row" style="margin-bottom:0%">
                       <div class="col-md-11">
                            <!-- Primary box -->
                            <div class="box box-solid box-primary">
                                <div class="box-header">
                                    <h3 class="box-title">Question: <span id="questionNo">1</span></h3>
                                   
                                </div>
                                <div class="box-body" style="background-color:#F5FFFA">
                                    
                                    <p id="questionContent">
                                        amber, microbrewery abbey hydrometer, brewpub ale lauter tun saccharification oxidized barrel.
                                        berliner weisse wort chiller adjunct hydrometer alcohol aau!
                                        sour/acidic sour/acidic chocolate malt ipa ipa hydrometer.
                                    </p>
                                </div><!-- /.box-body -->
                            </div><!-- /.box -->
                        </div><!-- /.col -->
                    </div>
                    <div class="row" >

                        <div class="col-md-11" >
                            <!-- Info box -->
                            <div class="box box-solid box-info" style="float:left">
                                <div class="box-header">
                                    <h3 class="box-title">Answer </h3>
                                    <div class="box-tools pull-right">
                                       
                                    </div>
                                </div>
                                <div class="box-body" style="background-color:#F5FFFA;">
                                     <p id="answerContent">
                                        amber, microbrewery abbey hydrometer, brewpub ale lauter tun saccharification oxidized barrel.
                                        berliner weisse wort chiller adjunct hydrometer alcohol aau!
                                        sour/acidic sour/acidic chocolate malt ipa ipa hydrometer.
                                    </p>
                               
                                </div><!-- /.box-body -->

                            </div><!-- /.box -->
                        </div><!-- /.col -->
                    </div><!-- /.row -->
                    <div class="row" >

                        <div class="col-md-11" >
                            <!-- Info box -->
                            <div class="box box-solid box-info" style="float:left">
                                <div class="box-header">
                                    <h3 class="box-title">Explanation </h3>
                                    <div class="box-tools pull-right">
                                       
                                    </div>
                                </div>
                                <div class="box-body" style="background-color:#F5FFFA;">
                                     <p id="explanationContent">
                                        amber, microbrewery abbey hydrometer, brewpub ale lauter tun saccharification oxidized barrel.
                                        berliner weisse wort chiller adjunct hydrometer alcohol aau!
                                        sour/acidic sour/acidic chocolate malt ipa ipa hydrometer.
                                    </p>
                               
                                </div><!-- /.box-body -->
                                
                            </div><!-- /.box -->
                        </div><!-- /.col -->
                    </div><!-- /.row -->
                    <div class="row" >

                        <div class="col-md-11" >
                            <!-- Info box -->
                            <div class="box box-solid box-info" style="float:left">
                                <div class="box-header">
                                    <h3 class="box-title">References</h3>
                                    <div class="box-tools pull-right">
                                       
                                    </div>
                                </div>
                                <div class="box-body" style="background-color:#F5FFFA;">
                                     <p id="referencesContent">
                                        amber, microbrewery abbey hydrometer, brewpub ale lauter tun saccharification oxidized barrel.
                                        berliner weisse wort chiller adjunct hydrometer alcohol aau!
                                        sour/acidic sour/acidic chocolate malt ipa ipa hydrometer.
                                    </p>
                               
                                </div><!-- /.box-body -->
                                
                            </div><!-- /.box -->
                        </div><!-- /.col -->
                    </div><!-- /.row -->
           
                </section><!-- /.content -->
        </div><!-- ./wrapper -->


        <!-- jQuery 2.0.2 -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <script src = "https://ajax.googleapis.com/ajax/libs/angularjs/1.3.3/angular.min.js"></script>
        <script src = "/prepareForInterview/resources/js/prepareForInterview.js"></script>
        <!-- Bootstrap -->
        <script src="/prepareForInterview/resources/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="/prepareForInterview/resources/js/AdminLTE/app.js" type="text/javascript"></script>

         <script type="text/javascript">
            $(function() {
               /*  $("#myModal").modal('show');
        //Flat red color scheme for iCheck
                $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
                    checkboxClass: 'icheckbox_flat-red',
                    radioClass: 'iradio_flat-red'
                });
                $('#chooseSectionId').on('click',function(){
                    var className = $('#anglerightId').attr('class');
                    $('#anglerightId').removeClass(className);
                    if(className == 'fa fa-angle-right')
                        $('#anglerightId').toggleClass('fa fa-angle-down');
                    else
                        $('#anglerightId').toggleClass('fa fa-angle-right');
                });
                $('#readInstructionsId').on('click',function(){
                    $("#myModal").modal('show');
                }); */
            });
        </script>

    </body>
</html>