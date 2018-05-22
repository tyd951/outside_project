<!DOCTYPE html>
<html lang="en">
<html>
    <head>
        <title>Final Project</title>
		<!-- ... -->
		<link rel="stylesheet" href="css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css" />
		<link rel="stylesheet" href="css/style.css">
		<script src="js/jquery.min.js"></script>
		
		<script type="text/javascript" src="js/moment.min.js"></script>
		<script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"></script>
		<script src="js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
		<script src="js/validator.min.js"></script>


		 
    </head>

    <body>
    	<?php
    		require_once('quickstart.php');
    		$client = getClient();
    		if(! is_a ($client, "Google_client")){
    			echo $client;
    		}
    		else{ 
        ?>
        		<nav class="navbar navbar-default">
				  <div class="container-fluid">
				    <!-- Brand and toggle get grouped for better mobile display -->
				    <div class="navbar-header">
				      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				        <span class="sr-only">Toggle navigation</span>
				        <span class="icon-bar"></span>
				        <span class="icon-bar"></span>
				        <span class="icon-bar"></span>
				      </button>
				      <a class="navbar-brand" href="#">Doctor Office</a>
				    </div>

				    <!-- Collect the nav links, forms, and other content for toggling -->
				    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				      <ul class="nav navbar-nav">
				        <li class="active"><a href="#">Home <span class="sr-only">(current)</span></a></li>
				        <li><a href="#">Link</a></li>
				      </ul>
				      <ul class="nav navbar-nav navbar-right">
				        <li class="dropdown">
				          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
				          <ul class="dropdown-menu">
				            <li><a href="#">Action</a></li>
				            <li><a href="#">Another action</a></li>
				            <li><a href="#">Something else here</a></li>
				            <li role="separator" class="divider"></li>
				            <li><a href="#">Separated link</a></li>
				          </ul>
				        </li>
				      </ul>
				    </div><!-- /.navbar-collapse -->
				  </div><!-- /.container-fluid -->
				</nav>
        		<div class="container">

        			<form action=" #" id="my_form" data-toggle="validator" role="form" onsubmit="return false">
        				<div class="form-group">
	        				<label class="text-center" for="name">Patient's Name</label>
	        				<input class="form-control" type='text' id='name' id='name' placeholder="First Last" required/>
	        			</div>
	        			<div class="form-group">
	        				<label class="text-center control-label" for="email">Email</label>
	        				<input class="form-control" type='email' id='email' name='email' placeholder="Email" data-error="This email address is invalid" required/>
	        				<div class="help-block with-errors"></div>
	        			</div>
<!--
						<div class="form-group">
	        				<label class="text-center" for="phone">Phone</label>
	        				<input class="form-control" type='text' name="phone" id='phone' required/>
	        			</div>
-->
					    <div class="form-group">
			                <div id="datetimepicker12">
			                	<label class="text-center" for="available_dates">Choose a Date</label>
    							<input class="form-control" type='text' id='available_dates' required autofocus />
			                </div>

					    </div>
	        			<div class="form-group">
	        				<label for="available_times">Available Times</label>
		    				<select id='available_times' class="form-control" required></select>
	        			</div>
	        			<div class="form-group">
	        				<input class="btn btn-default" type="submit" id='submit' value="Make appointment">
	        			</div>
        			</form>
					

	    			<h3 id='dump'></h3>

        		</div>
    			
    			<script src="js/ajax.js"></script>
				<script>
					var tmr = new Date();
			        tmr.setHours(8);
					tmr.setDate(tmr.getDate() + 1);
					$('#available_dates').click(function () {
					   $('#datetimepicker12').datetimepicker('show');
					});

					$(function () {
						$('#datetimepicker12 input').datetimepicker({
							useCurrent: false,
							locale: 'it',
							format:'YYYY-MM-DD',
							minDate: new Date(tmr),
						    inline: true,
						    sideBySide: true
						});
					});
					
				</script>
    		<?php }
    	    ?>
    </body>

</html>