Yida Tao
-------------------------------------------------------------------
instructions:
- only display avaiable time that after today
- patient can select the time they want
- require name and email, will send reminder to the email
- check the input's format
- after submit, patients should get a email, ask them create event on their calendar, they can choose yes/no/maybe
- new event will diaplay on doctor office's calendar
-------------------------------------------------------------------
Prerequisites:
*PHP 5.4 or greater with the command-line interface (CLI) and JSON extension installed.
*The Composer dependency management tool.
*A Google account with Google Calendar enabled.
*Must have internet.
*HTML server.
-------------------------------------------------------------------
tech stack:
html, css, js, jquery, bootstrap 3, bootstrap Validator, datetimepicker, php, ajax
-------------------------------------------------------------------
Setup apache server and php: 
(if you already have the apache server and php installed, skip this part)

Windows: follow the steps in the following link
	
	https://make.wordpress.org/core/handbook/tutorials/installing-a-local-server/xampp/
	

Linux: follow the steps in the following link, install apache and php

	https://www.digitalocean.com/community/tutorials/how-to-install-linux-apache-mysql-php-lamp-stack-on-ubuntu

Mac Apache2 localhost setup: 

	https://discussions.apple.com/docs/DOC-12034

-------------------------------------------------------------------
Setup

step 1: Download all the source files

step 2: Create a new folder in html/public_html/htdocs(for xampp) folder

step 2: Put source files into it (server side have the php installed)

step 3: Open the terminal in the our source code folder

	option 1)

		go to website: https://developers.google.com/calendar/quickstart/php 
		
		only do the following
		
		1. Setup composer in the directory where our files is

		2. Install google client library in the directory where our files is

	option 2)

		Find the directory where all the source files are located on terminal

		Copy the following code into the terminal and run: 

		1.setup composer

		php -r "copy('https://getcomposer.org/installer', 'composer-setup.php');"
		php -r "if (hash_file('SHA384', 'composer-setup.php') === 	'544e09ee996cdf60ece3804abc52599c22b1f40f4323403c44d44fdfdd586475ca9813a858088ffbc	1f233e9b180f061') { echo 'Installer verified'; } else { echo 'Installer corrupt'; 	unlink('composer-setup.php'); } echo PHP_EOL;"
		php composer-setup.php
		php -r "unlink('composer-setup.php');"

		2. install google client library

		php composer.phar require google/apiclient:^2.0

step 4: I.   run index.php in ssh, console will output a link
		
		II.  Copy the link in the console to browser, login to a google account which the google calendar account 

		III. Get permission code from google account

		IV.  Enter code into ssh

		Thats all setup

step 5: Go to browser,open index.php, it should work.
