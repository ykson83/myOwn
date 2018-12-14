var SpaceHipster = SpaceHipster || {};

//title screen
SpaceHipster.Game = function(){};
var moving=100;
var moving2=200;
var moving3=150;
var moving4=200;
var ticket=true; //key 먹으면 true로 바꿔
var movingStage = false;
var repeat = true;
var spareLife = 3;
var lives =[];
var movingKey;
var isclock = false;
var door;
var baddie = [];
var baddies = [];
var fire = [];
var fires = [];
var tortoise=[];
var tortoises=[];



SpaceHipster.Game.prototype = {
  
		init: function(params, param2){
			this.score = params == null ? 0 : params;
			spareLife = param2 == null ? 3 : param2;
		},
		
		
  preload: function() {

    this.load.image('back', 'assets/level1/background.png'); //숲 배경
    this.load.image('grassground', 'assets/level1/grassground.png'); //바닥
    this.load.image('ground', 'assets/level1/ground.png'); //바닥
    this.load.image('log', 'assets/level1/log.png'); //짬푸 짬푸 바
    this.load.image('star', 'assets/level1/star.png'); //별
    this.load.spritesheet('door', 'assets/level1/door.png', 60, 81); //문
    this.load.spritesheet('tigger', 'assets/level1/tigger1.png', 150, 224); //티거
    this.load.spritesheet('dude', 'assets/level1/dude.png', 32, 48); //듀드(플레이어)
    this.load.spritesheet('baddie2', 'assets/level1/baddie.png', 33, 34, 10);
    this.load.spritesheet('baddie', 'assets/level1/baddie.png', 33, 34, 10);
    this.load.spritesheet('fire', 'assets/level1/fire.png', 61.5, 99, 9);
    this.game.load.image('clock', 'assets/level1/ice.png');
    this.game.load.image('shoe', 'assets/level1/buddy.png');
    this.game.load.image('heart', 'assets/level1/heart.png');
    this.game.load.image('key', 'assets/level1/goldenkey.png');
    this.game.load.image('tortoise', 'assets/level1/tortoise.png');
    
  },
  create: function() {
  	//set world dimensions
    this.game.world.setBounds(0, 0, 2000, 600);
    
    //background
    this.game.add.tileSprite(0, 0, 2000, 600, 'back');
    
    this.game.physics.startSystem(Phaser.Physics.ARCADE);
    
    this.platforms = this.game.add.group();
    this.platforms.enableBody = true;
    

    
    door = this.game.add.sprite(200, 470, 'door');
    this.game.physics.arcade.enable(door);
    door.body.collideWorldBounds = true;
    door.body.immovable = true;
    door.body.checkCollision.left = false;
    door.body.checkCollision.right = false;
    door.body.checkCollision.up = false;
    
    this.grassground = this.game.add. image(0, this.game.world.height - 71, 'grassground');
    
    this.ground = this.platforms.create(0, this.game.world.height - 49, 'ground');
    this.ground.scale.setTo(1, 1);
    this.ground.body.immovable = true;
    
    this.log = this.platforms.create(-150, 400, 'log');//
    this.log.scale.setTo(0.8, 0.7);
    this.log.body.immovable = true;
    
    this.log = this.platforms.create(300, 200, 'log'); //배디1
    this.log.scale.setTo(1.1, 0.7);
    this.log.body.immovable = true;
    
    this.log = this.platforms.create(360, 440, 'log');
    this.log.scale.setTo(0.8, 0.7);
    this.log.body.immovable = true;
    
    this.log = this.platforms.create(680, 320, 'log'); //시계
    this.log.scale.setTo(0.8, 0.7);
    this.log.body.immovable = true;
    
    this.log = this.platforms.create(1020, 200, 'log');
    this.log.scale.setTo(0.8, 0.7);
    this.log.body.immovable = true;
    
    this.log = this.platforms.create(1400, 440, 'log'); //양쪽 불
    this.log.scale.setTo(0.8, 0.7);
    this.log.body.immovable = true;
    
    this.log = this.platforms.create(1600, 320, 'log');
    this.log.scale.setTo(0.8, 0.7);
    this.log.body.immovable = true;
    
    this.log = this.platforms.create(1910, 200, 'log');//불
    this.log.scale.setTo(0.8, 0.7);
    this.log.body.immovable = true;
    
    this.log = this.platforms.create(1500, 100, 'log'); //열쇠
    this.log.scale.setTo(1, 0.7);
    this.log.body.immovable = true;
    
    
    //create player
    this.player = this.game.add.sprite(50, this.game.world.height - 150, 'dude');
//    this.baddie = this.game.add.sprite(500, this.game.world.height - 150, 'baddie');
    this.tigger = this.game.add.sprite(300, this.game.world.height - 300, 'tigger');
    this.tigger.scale.setTo(0.5, 0.5);
    this.tigger2 = this.game.add.sprite(1700, 300, 'tigger');
    this.tigger2.scale.setTo(0.4, 0.4);
 
    
    //baddies 만들기
    baddie[0] = {'x':300 , 'mx':600 , 'y':150, 'moving':100 };
    baddie[1] = {'x':340 , 'mx':560 , 'y':380, 'moving':100 };
    baddie[2] = {'x':680 , 'mx':880 , 'y':270, 'moving':100 };
    baddie[3] = {'x':1000 , 'mx':1220 , 'y':100, 'moving':100 };
    baddie[4] = {'x':1600 , 'mx':1800 , 'y':270, 'moving':100 };
    baddie[5] = {'x':1500 , 'mx':1750 , 'y':60, 'moving':100 };
    
    for(i=0; i<=5; i++){
    	baddies[i] = this.game.add.sprite(baddie[i].x, baddie[i].y, 'baddie');
    	this.game.physics.arcade.enable(baddies[i]);
    	baddies[i].body.bounce.y = 0.1;
    	baddies[i].body.gravity.y = 300;
    	baddies[i].body.collideWorldBounds = true;
    	
    	baddies[i].animations.add('left2', [0, 1], 5, true);
    	baddies[i].animations.add('right2', [2, 3], 5, true);
    	baddies[i].animations.add('leftDead', [4, 5, 6], 5, false);
    	baddies[i].animations.add('rightDead', [9, 8, 7], 5, false);
    }
    
    
    this.game.physics.arcade.enable(this.player);
//    this.game.physics.arcade.enable(this.baddie);
    this.game.physics.arcade.enable(this.tigger);
    this.game.physics.arcade.enable(this.tigger2);
    
    this.player.body.bounce.y = 0.1;
    this.player.body.gravity.y = 1000;
    this.player.body.collideWorldBounds = true;
    this.player.scale.setTo(1.5, 1.5);
	
    this.tigger.body.bounce.y = 0.1;
    this.tigger.body.gravity.y = 300;
    this.tigger.body.collideWorldBounds = true;
    
    this.tigger2.body.bounce.y = 0.1;
    this.tigger2.body.gravity.y = 300;
    this.tigger2.body.collideWorldBounds = true;
    
    this.player.animations.add('left', [0, 1, 2, 3], 5, true);
    this.player.animations.add('right', [5, 6, 7, 8], 5, true);
    
    this.tigger.animations.add('tiggerRight', [0, 1, 2, 3, 4], 4, true);
    this.tigger.animations.add('tiggerLeft', [9,8, 7, 6, 5], 4, true);
    this.tigger2.animations.add('tigger2Right', [0, 1, 2, 3, 4], 4, true);
    this.tigger2.animations.add('tigger2Left', [9, 8, 7, 6, 5], 4, true);
    
    door.animations.add('open', [0, 1, 2, 3, 4], 5, false);
    
    this.stars = this.game.add.group();
    this.stars.enableBody = true;
    for (var i = 0; i <	20; i++)
    {
        //  Create a star inside of the 'stars' group
        var star = this.stars.create(i * 98, 0, 'star');

        //  Let gravity do its thing
        star.body.gravity.y = 300;

        //  This just gives each star a slightly random bounce value
        star.body.bounce.y = 0.7 + Math.random() * 0.2;
    }
    
    
    //player initial score of zero
    this.playerScore = this.game.add.text(16, 16, 'score: '+this.score, { fontSize: '32px', fill: '#000' });
    this.playerScore.fixedToCamera = true;
    this.playerScore.cameraOffset.setTo(16, 16);
    
    //enable player physics
    this.player.body.collideWorldBounds = true;

    //the camera will follow the player in the world
    this.game.camera.follow(this.player);
    
    this.cursors = this.game.input.keyboard.createCursorKeys();
//    this.cursors2 = this.game.input.keyboard.addKey(Phaser.Keyboard.SPACEBAR);
    
    //item
    this.items = this.game.add.group();
    this.items.enable = true;
    this.items.enableBody = true; //중력을 주기 전에..물리 관련 항목 활성화
    
    
    //obstacles
    this.obstacles = this.game.add.group();
    this.obstacles.enable = true
    this.obstacles.enableBody = true; //중력을 주기 전에..물리 관련 항목 활성화
    
    //fire
    fire[0] = {'x': 1400, 'y': 391};
    fire[1] = {'x': 1605, 'y': 391};
    fire[2] = {'x': 1965, 'y': 150};
    fire[3] = {'x': 665, 'y': 360};
    fire[4] = {'x': 1310, 'y': 300};
    fire[5] = {'x': 1850, 'y': 400};
    fire[6] = {'x': 460, 'y': 480};
    
    for(i=0; i<=6; i++){
    	fires[i] = this.obstacles.create(fire[i].x, fire[i].y, 'fire');
    	fires[i].scale.setTo(0.5, 0.5);
    	fires[i].body.immovable = true;
    	fires[i].animations.add('play', [0, 1, 2, 3, 4, 5, 6, 7, 8], 5, true);
    }
    

   
  //목숨목숨
    for(var i=0; i<spareLife; i++){
    	var life = lives[i];
    	lives[i] = this.game.add.image(1400-i*25, 10, 'dude');
    	lives[i].scale.setTo(0.7, 0.7);
    	lives[i].frame = 4;
    }
    
    console.log(spareLife);
    console.log(lives.length);
    //미니미 목숨 카메라
    for(var i=0; i<spareLife; i++){
    lives[i].fixedToCamera = true;
    lives[i].cameraOffset.setTo(lives[i].position.x, 10);
    }

    
    //shoe
    this.shoe = this.items.create(170, 350,'shoe');
    this.shoe.scale.setTo(0.9, 0.9);
    this.game.physics.arcade.enable(this.shoe);
    this.shoe.body.bounce.y = 0.1;
    this.shoe.body.gravity.y = 300;
    this.shoe.body.collideWorldBounds = true;
    this.shoe.enableBody = true;
    
    //shoe2
    this.shoe2 = this.items.create(820, 480,'shoe');
    this.shoe2.scale.setTo(0.9, 0.9);
    this.game.physics.arcade.enable(this.shoe2);
    this.shoe2.body.bounce.y = 0.1;
    this.shoe2.body.gravity.y = 300;
    this.shoe2.body.collideWorldBounds = true;
    this.shoe2.enableBody = true;
	
	
	//시계
    this.clock = this.items.create(900, 0, 'clock');
    this.clock.scale.setTo(0.06, 0.06);
    this.clock.body.gravity.y = 300;
    this.clock.body.bounce.y = 0.1;
    this.clock.body.collideWorldBounds = true;
    this.clock.enableBody = true;
	
	
	//heart
    this.heart = this.items.create(30, 200, 'heart');
    this.heart.scale.setTo(0.02, 0.02);
    this.heart.body.gravity.y = 300;
    this.heart.body.bounce.y = 0.1;
    this.heart.body.collideWorldBounds = true;
    this.heart.enableBody = true;
	
    this.heart2 = this.items.create(1120, 200, 'heart');
    this.heart2.scale.setTo(0.02, 0.02);
    this.heart2.body.gravity.y = 300;
    this.heart2.body.bounce.y = 0.1;
    this.heart2.body.collideWorldBounds = true;
    this.heart2.enableBody = true;
	
	
	//key
    this.key = this.items.create(1600, 15, 'key');
    this.key.scale.setTo(0.5, 0.5);
    this.game.physics.arcade.enable(this.key);
    this.key.body.bounce.y = 0.1;
    this.key.body.gravity.y = 50;
    this.key.body.collideWorldBounds = true;
    this.key.enableBody = true;
	
	
	//tortoise
    tortoise[0] = {'x':440, 'y':150};
    tortoise[1] = {'x':900, 'y':480};
    tortoise[2] = {'x':1130, 'y':130};
    tortoise[3] = {'x':445, 'y':128};
    
    for(i=0; i<3; i++){
    	
    	tortoises[i] = this.items.create(tortoise[i].x, tortoise[i].y, 'tortoise');
    	tortoises[i].scale.setTo(0.8, 0.8);
    	tortoises[i].body.gravity.y = 300;
    	tortoises[i].body.bounce.y = 0.1;
    }
    
    
  //tigger 이동
  	this.tigger.animations.play('tiggerRight');
  	this.tigger2.animations.play('tigger2Right');
    
  },
  update: function() {

	
	this.game.physics.arcade.collide(this.stars, this.platforms);
	this.game.physics.arcade.collide(this.player, this.platforms);
	this.game.physics.arcade.collide(this.tigger, this.platforms);
	this.game.physics.arcade.collide(this.tigger2, this.platforms);
	this.game.physics.arcade.collide(baddies, this.platforms);  
	this.game.physics.arcade.overlap(this.player, baddies, this.dead, null, this);
	this.game.physics.arcade.overlap(this.player, this.stars, this.collectStar, null, this);
	this.game.physics.arcade.overlap(this.player, door, this.nextLevel, null, this);
	this.game.physics.arcade.collide(this.player, fires, this.fireDead, null, this);
	this.game.physics.arcade.collide(this.player, this.tigger, this.tiggerDead, null, this);
	this.game.physics.arcade.collide(this.player, this.tigger2, this.tiggerDead2, null, this);
	this.game.physics.arcade.collide(this.items, this.platforms);
	
	
	this.game.physics.arcade.overlap(this.player, this.shoe, this.getShoe, null, this);
	this.game.physics.arcade.overlap(this.player, this.shoe2, this.getShoe2, null, this);
	this.game.physics.arcade.overlap(this.player, this.clock, this.getClock, null, this);
	this.game.physics.arcade.overlap(this.player, this.key, this.getKey, null, this);
	this.game.physics.arcade.overlap(this.player, this.heart, this.getHeart, null, this);
	this.game.physics.arcade.overlap(this.player, this.heart2, this.getHeart2, null, this);
	this.game.physics.arcade.overlap(this.player, this.heart3, this.getHeart3, null, this);
	this.game.physics.arcade.overlap(this.player, tortoises, this.getTortoise, null, this);
	    
	

	
	this.player.body.velocity.x = 0;
//	this.baddies.body.velocity.x = 0;
	
	if (this.cursors.left.isDown)
    {
		this.player.body.velocity.x = -moving2;
		this.player.animations.play('left');
    }
    else if (this.cursors.right.isDown)
    {
    	this.player.body.velocity.x = moving2;
    	this.player.animations.play('right');
    }else
    {
    	this.player.animations.stop();
    	this.player.frame = 4;
    }
	
	//점프점프
//	if (this.cursors2.isDown){
//    	this.player.body.velocity.y = -500;
//    }
	if (this.cursors.up.isDown && this.player.body.touching.down)
    {
    	this.player.body.velocity.y = -500;
    }
	
	
	//baddie 이동방향
	if(!isclock){
		for(i=0; i<6; i++){
				
			if(baddies[i].position.x >= baddie[i].mx){
				baddie[i].moving = -100;
			}else if(baddies[i].position.x < baddie[i].x){
				baddie[i].moving = 100;
			}
			
			if(baddie[i].moving > 0 ){
				baddies[i].body.velocity.x = baddie[i].moving;
				baddies[i].animations.play('right2');
			}else if(baddie[i].moving<0){
				baddies[i].body.velocity.x = baddie[i].moving;
				baddies[i].animations.play('left2');
			}else{
				baddies[i].body.velocity.x = baddie[i].moving;
			} 
		}
	}else{
		for(i=0; i<6; i++){
			baddies[i].body.velocity.x = 0;
		}
		setTimeout(function() {
			isclock=false;
		}, 2000)
		}
	
	//baddie 이동할때 애니메이션
//	for(i=0; i<6; i++){	
//		if(baddie[i].moving > 0 ){
//			baddies[i].body.velocity.x = moving;
//			baddies[i].animations.play('right2');
//		}else if(moving<0){
//			baddies[i].body.velocity.x = moving;
//			baddies[i].animations.play('left2');
//		}
//	}
	
	
	//tigger
	if(this.tigger.body.blocked.right){
		console.log('tigger 오른쪽 부딪힘');
		this.tigger.animations.play('tiggerLeft');
		moving3 = -150;
	}else if(this.tigger.body.blocked.left){
		console.log('tigger 왼쪽 부딪힘');
		this.tigger.animations.play('tiggerRight');
		moving3 = 150;
	}

	this.tigger.body.velocity.x = moving3;
	
	if (this.tigger.body.touching.down)
    {
    	this.tigger.body.velocity.y = -300;
    }
	
	//tigger2
	if(this.tigger2.body.blocked.right){
		console.log('tigger2 오른쪽 부딪힘');
		this.tigger2.animations.play('tigger2Left');
		moving4 = -150;
	}else if(this.tigger2.body.blocked.left){
		console.log('tigger2 왼쪽 부딪힘');
		this.tigger2.animations.play('tigger2Right');
		moving4 = 150;
	}

	this.tigger2.body.velocity.x = moving4;
	
	if (this.tigger2.body.touching.down)
    {
    	this.tigger2.body.velocity.y = -300;
    }
	
	
	
	for(i=0; i<=6; i++){
		fires[i].animations.play('play');
	}
	
	
	//신발 둥둥
    if(this.shoe.position.y >= 400){
    	this.shoe.body.velocity.y = -100;
    }
    else if(this.shoe.position.y < 350){
    	this.shoe.body.velocity.y = 100;
    }
    
	//신발 둥둥2
    if(this.shoe2.position.y >= 490){
    	this.shoe2.body.velocity.y = -100;
    }
    else if(this.shoe2.position.y < 420){
    	this.shoe2.body.velocity.y = 100;
    }
	
	
	//열쇠 둥둥
	if(this.key.position.y >= 450){
		this.key.body.velocity.y = -25;
	}
	else if(this.key.position.y < 430){
		this.key.body.velocity.y = 25;
	}
	
	
  },
  
  collectStar: function(player, star) {
	  star.kill();
	  this.score += 10;
	  this.playerScore.text = 'Score: ' + this.score;
  },
  
  dead: function(player, baddie) {
	  if(this.player.body.touching.right || this.player.body.touching.left){
		  this.player.kill();
		  
		  this.deadPlayer = this.game.add.sprite(this.player.position.x, this.player.position.y, 'dude');
		  this.deadPlayer.scale.setTo(1.5, 1.5);
		  this.deadPlayer.frame = 4;
		  this.game.physics.arcade.enable(this.deadPlayer);
			
		  this.deadPlayer.body.velocity.y = -600;
		  this.deadPlayer.body.gravity.y = 1500;
		  this.deadPlayer.body.collideWorldBounds = false;
		  this.deadPlayer.body.checkCollision.down = false;
		  this.deadPlayer.body.checkCollision.up = false;
//		  this.deadPlayer.body.checkCollision.left = false;
			
			  if(spareLife==0){
				  this.game.time.events.add(1500, this.gameOver, this);
			  }
			  else{
				  //목숨 줄이기
		    	  spareLife -= 1;
				  var n = lives.length;
				  console.log('미니미 줄어들기 전'+n);
				  
				  lives[n-1].kill();
				  lives.pop();
				  console.log('줄어든 후' + lives.length);
				  
				  this.game.time.events.add(1500, this.reload, this);
			  }
			  
		
		}else if(this.player.body.touching.down){
			baddie.kill();
			var dead = this.game.add.sprite(baddie.body.x, baddie.body.y, 'baddie2');
			dead.animations.add('dead', [9,8,7], 5, false);
			dead.animations.play('dead');
			dead.alpha = 1;
			
			//깜빡거림 효과
			this.game.add.tween(dead).to( { alpha: 0 }, 500, Phaser.Easing.Linear.None, true, 0, 1, false);
		    
		    //점수 추가
			this.score += 50;
			this.playerScore.text = 'Score: ' + this.score;
			
			this.player.body.velocity.y = -500;

		}
  },
  
  fireDead: function(player, fire){
		//점프뛰기
		this.player.kill();
	
		this.deadPlayer = this.game.add.sprite(this.player.position.x, this.player.position.y, 'dude');
		this.deadPlayer.scale.setTo(1.5, 1.5);
		this.deadPlayer.frame = 4;
		this.game.physics.arcade.enable(this.deadPlayer);
		
		this.deadPlayer.body.velocity.y = -600;
		this.deadPlayer.body.gravity.y = 1500;
		this.deadPlayer.body.collideWorldBounds = false;
		this.deadPlayer.body.checkCollision.down = false;
		this.deadPlayer.body.checkCollision.up = false;
		this.deadPlayer.body.checkCollision.left = false;
		
		if(spareLife==0){
			  this.game.time.events.add(1500, this.gameOver, this);
		  }else{
		
		
		//목숨 줄이기
		spareLife -= 1;
		var n = lives.length;
		console.log('미니미 줄어들기 전'+n);
		lives[n-1].kill();
		lives.pop();
		console.log('줄어든 후' + lives.length);
		
		this.game.time.events.add(1500, this.reload, this);
		  }
  },
  
  tiggerDead: function(){
	    this.player.kill();
		
		this.deadPlayer = this.game.add.sprite(this.player.position.x, this.player.position.y, 'dude');
		this.deadPlayer.scale.setTo(1.5, 1.5);
		this.deadPlayer.frame = 4;
		this.game.physics.arcade.enable(this.deadPlayer);
		
		this.deadPlayer.body.velocity.y = -600;
		this.deadPlayer.body.gravity.y = 1500;
		this.deadPlayer.body.collideWorldBounds = false;
		this.deadPlayer.body.checkCollision.down = false;
		this.deadPlayer.body.checkCollision.up = false;
		this.deadPlayer.body.checkCollision.left = false;
		
		if(spareLife==0){
			  this.game.time.events.add(1500, this.gameOver, this);
		}
		else{
			//목숨 줄이기
			spareLife -= 1;
			var n = lives.length;
			console.log('미니미 줄어들기 전'+n);
			lives[n-1].kill();
			lives.pop();
			console.log('줄어든 후' + lives.length);
			
			this.game.time.events.add(1500, this.reload, this);
		}
  },
  
  tiggerDead2: function(){
	    this.player.kill();
		
		this.deadPlayer = this.game.add.sprite(this.player.position.x, this.player.position.y, 'dude');
		this.deadPlayer.scale.setTo(1.5, 1.5);
		this.deadPlayer.frame = 4;
		this.game.physics.arcade.enable(this.deadPlayer);
		
		this.deadPlayer.body.velocity.y = -600;
		this.deadPlayer.body.gravity.y = 1500;
		this.deadPlayer.body.collideWorldBounds = false;
		this.deadPlayer.body.checkCollision.down = false;
		this.deadPlayer.body.checkCollision.up = false;
		this.deadPlayer.body.checkCollision.left = false;
		
		if(spareLife==0){
			  this.game.time.events.add(1500, this.gameOver, this);
		}
		else{
			//목숨 줄이기
			spareLife -= 1;
			var n = lives.length;
			console.log('미니미 줄어들기 전'+n);
			lives[n-1].kill();
			lives.pop();
			console.log('줄어든 후' + lives.length);
			
			this.game.time.events.add(1500, this.reload, this);
		}
},
  
  getShoe: function(player, shoe){
		console.log('shoe');
		this.shoe.kill();
		moving2 = 600;
		setTimeout(function() {
			moving2 = 200;
		}, 5000)
	},	

  getShoe2: function(player, shoe){
		console.log('shoe2');
		this.shoe2.kill();
		moving2 = 800;
		setTimeout(function() {
			moving2 = 200;
		}, 5000)
	},
		


	getClock: function (){
		
		console.log('clock');
		this.clock.kill();
		isclock = true;
	},

	getHeart: function (){
		console.log('heart');
		this.heart.kill();
		spareLife += 1;
		console.log(spareLife);
		
		var life = lives[spareLife-1];
		lives[spareLife-1] = this.game.add.image(1400-(spareLife-1)*25, 10, 'dude');
		lives[spareLife-1].scale.setTo(0.7, 0.7);
		lives[spareLife-1].frame = 4;
		
	    for(var i=0; i<spareLife; i++){
	        lives[i].fixedToCamera = true;
	        lives[i].cameraOffset.setTo(lives[i].position.x, 10);
	        }
		
	},

	getHeart2: function(){
		console.log('heart');
		this.heart2.kill();
		spareLife += 1;
		console.log(spareLife);
		
		var life = lives[spareLife-1];
		lives[spareLife-1] = this.game.add.image(1400-(spareLife-1)*25, 10, 'dude');
		lives[spareLife-1].scale.setTo(0.7, 0.7);
		lives[spareLife-1].frame = 4;
		
	    for(var i=0; i<spareLife; i++){
	        lives[i].fixedToCamera = true;
	        lives[i].cameraOffset.setTo(lives[i].position.x, 10);
	        }
		
	},
	
	

	getKey: function (){
		console.log('key');
		this.key.kill();
		ticket = true;
	
		//이동할 열쇠
		movingKey = this.items.create(this.key.position.x, this.key.position.y, 'key');
		movingKey.scale.setTo(0.5, 0.5);
	    
	    var x = this.game.add.tween(movingKey);
//	    var y = this.game.add.tween(movingKey.scale);
	    console.log(movingKey.scale);
	    x.to({x:1830, y:15}, 2000, Phaser.Easing.Sinusoidal.InOut, true, 0, 0, false);
//	    y.to({x:0.16, y:0.16 }, 2000, Phaser.Easing.Sinusoidal.InOut, true, 0, 0, false);
	    x.onComplete.add(a,this);
	    
	    
	    function a(){
	    	movingKey.fixedToCamera = true;
		    movingKey.cameraOffset.setTo(1430, 15);
	    }
	    
	},

	getTortoise: function(){
		console.log('tortoise');
//		this.tortoise.kill();
		moving2 = 100;
		setTimeout(function() {
			moving2 = 200;
		}, 4000);
	},
	
	movingKeykill : function(){
		movingKey.kill();
	},
	
  reload: function(){
	  
	  moving2 = 200;
	  ticket = false;
	  movingStage = false;
	  moving3 = 150;
	  moving4 = 150;
	  
	  this.game.state.start('Game', true, false, this.score, spareLife);
	  
  },
  
  nextLevel: function(player, door) {  
	  console.log('nextLevel1');
	  if( repeat && ticket && this.cursors.down.isDown){
		  door.animations.play('open');
		  repeat = false;
		  this.tigger.kill();
		  this.tigger2.kill();
		  
		  setTimeout(function(){
			  movingStage = true;
			  console.log('다음 스테이지를 이동할 수 있을까' + movingStage);
		  }, 2000);
	  }
	  
		  if( movingStage){
			  console.log('nextLevel2');
			  moving2 = 200;
			  ticket = false;
			  movingStage = false; 
			  
			  console.log('문 또열리면 안돼!' + repeat);
			  console.log('다음 스테이지 이동하고 싶더');
			  var params = {'score':this.score, 'spareLife':spareLife};
			  this.game.state.start('Level2', true, false, params);  
		  };

  },

  gameOver: function() {    
	  moving2 = 200;
	  ticket = false;
	  movingStage = false;
    //pass it the score as a parameter 
    this.game.state.start('GameOver', true, false, this.score);
  },
  
//  render:function(){
//	this.game.debug.bodyInfo(this.player, 32, 32);
////	this.game.debug.body(this.tigger);
//  }
};

/*
TODO

-audio
-asteriod bounch
*/
