<?php
if(isset($_SERVER['HTTP_X_REQUESTED_WITH']) && 
		strtolower($_SERVER['HTTP_X_REQUESTED_WITH'])==='xmlhttprequest'){

	require_once('quickstart.php');
	$the_html =$_GET['action']();

	echo $the_html;
}
else{
	echo "don't be silly";
	die();
}

function get_times(){
	$date = new DateTime($_GET['date']);
	$day_after = new DateTime($_GET['date']);
	$day_after->add(new DateInterval('P1D'));
	$innerHTML_arr =[];
	for($i=9;$i<17;$i++){
		$innerHTML_arr[] = "<option value='$i'>$i:00</option>";
	}


	$client = getClient();
	$service = new Google_Service_Calendar($client);
	// Print the next 10 events on the user's calendar.
	$calendarId = 'primary';
	$optParams = array(
		'timeMin' => date('c',$date->getTimestamp()),
		'timeMax' => date('c',$day_after->getTimestamp()),
	);
	$results = $service->events->listEvents($calendarId, $optParams);

	$the_html="";
	foreach ($results->getItems() as $event) {
		$s = new DateTime($event->start->dateTime);
		$start = date('G',$s->getTimestamp());
		unset($innerHTML_arr[$start-9]);
	}
	$the_html.=implode("",$innerHTML_arr);

	return $the_html;

}

function schedule_me(){
	createEvent();
	$the_html = "
		Your appointment has been Scheduled!
	";
	return $the_html;
}

function createEvent(){
	$name = $_GET['name'];
	$email = $_GET['email'];
	$date_time = new DateTIme($_GET['date']);
	$date_time->setTime($_GET['time'],0);
	$end_date_time = new DateTIme($_GET['date']);
	$end_date_time->setTime($_GET['time']+1,0);


	$client = getClient();
	$service = new Google_Service_Calendar($client);
	$event = new Google_Service_Calendar_Event(array(
		//summary is title of event
	  'summary' => $name."'s Appointment",
	  'location' => 'doctor office\'s address',
	  'description' => 'Appointment made by '.$name,
	  'start' => array(
	    'dateTime' => date('c',$date_time->getTimestamp()),
	    'timeZone' => 'America/New_York',
	  ),
	  'end' => array(
	    'dateTime' => date('c',$end_date_time->getTimestamp()),
	    'timeZone' => 'America/New_York',
	  ),
	  'attendees' => array(
	    array('email' => $email),
	  ),
	  'guestsCanInviteOthers' => FALSE,
	  'visibility' => "private",

	  'reminders' => array(
	    'useDefault' => FALSE,
	    'overrides' => array(
	      array('method' => 'email', 'minutes' => 24 * 60),
	      array('method' => 'popup', 'minutes' => 10),
	    ),
	  ),
	));

	//reminder
	$params = ["sendNotifications" => "true"];

	$calendarId = 'primary';
	$event = $service->events->insert($calendarId, $event, $params);
}


?>