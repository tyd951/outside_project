/*
Create by Learn Web Developement
Youtube channel : https://www.youtube.com/channel/UC8n8ftV94ZU_DJLOLtrpORA
*/

let cvs = document.getElementById('snake');
let ctx = cvs.getContext('2d');

const box = 32;
const ground = new Image();
ground.src = "img/ground.png";
const foodimg = new Image();
foodimg.src = "img/food.png";

ctx.font = "45px Gugi";

let score = 0;
let snake = [];
snake[0] = {x: 8 * box, y: 12*box};
snake[1] = {x: 8 * box, y: 13*box};
snake[2] = {x: 8 * box, y: 14*box};

let food = {
	x:Math.floor(Math.random()*16+1)*box,
	y:Math.floor(Math.random()*14+3)*box
};


let direction = 'UP';

function draw(){
	ctx.drawImage(ground,0,0);
	let color = 'red';
	for(let piece of snake){
		ctx.fillStyle = color;
		if(color ==='red') color = 'black';
		else color = 'red';
		ctx.fillRect(piece.x,piece.y,box,box);
	}

	ctx.drawImage(foodimg,food.x,food.y);
	ctx.fillStyle='white';
	ctx.fillText(score,2*box,1.6*box);


	let newHead = {x:snake[0].x, y:snake[0].y};
	if(direction === 'UP'){
		newHead.y -= (1*box);
	}
	else if(direction === 'DOWN'){
		newHead.y += (1*box);
	}
	else if(direction === 'LEFT'){
		newHead.x -= (1*box);
	}
	else if(direction === 'RIGHT'){
		newHead.x += (1*box);
	}

	if(can_eat(newHead)){
		food = {
			x:Math.floor(Math.random()*16+1)*box,
			y:Math.floor(Math.random()*14+3)*box
		};
		score++;
	}
	else{
		snake.pop();
	}

	if(collision(newHead)){
		//game over
		clearInterval(game_id);
	}

	snake.unshift(newHead);
	
}

document.addEventListener('keydown',direction_change);

function direction_change(event){
	if(event.key ==='ArrowUp' && direction !== 'DOWN'){
		direction = 'UP';
	}
	else if(event.key ==='ArrowDown' && direction !== 'UP'){
		direction = 'DOWN';
	}
	else if(event.key ==='ArrowLeft' && direction !== 'RIGHT'){
		direction = 'LEFT';
	}
	else if(event.key ==='ArrowRight' && direction !== 'LEFT'){
		direction = 'RIGHT';
	}
}


function collision(newHead){
	if( newHead.x< 1*box ||
		newHead.x>17*box ||
		newHead.y< 3*box ||
		newHead.y>17*box){

		return true;
	}
	for(piece of snake){
		if(newHead.x === piece.x && newHead.y === piece.y){
			return true;
		}
	}
	return false;
}

function can_eat(newHead){
	if(newHead.x === food.x && newHead.y === food.y){
		return true;
	}

	return false;
}

// 20 ms to download the image, then call draw function
// call draw right away not gonna work
game_id = setInterval(draw,110);