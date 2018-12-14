var SpaceHipster = SpaceHipster || {};
var ghosts=[];
var ghost=[];
var score=0;
var moving=100;
var baddies=[];
var baddie=[];
var door;
var doorClose;
var ifOpen;
var stageClear;
var finalCoin = [];

var spareLife = 3;
var lives =[];

//title screen
SpaceHipster.Level2 = function(){};
 
SpaceHipster.Level2.prototype = {
	init: function(param){
		score = param.score == null ? 0 : param.score;
		spareLife = param.spareLife == null ? 3 : param.spareLife;
		
  },
	preload: function() {
		  	//load game assets
		    this.load.image('background', 'assets/Level2/background.png');
		    this.load.image('ground', 'assets/Level2/ground.png');
		    this.load.image('tile', 'assets/Level2/tile.png');
		    this.load.image('tileBack', 'assets/Level2/tileback.png');
		    this.load.image('vTile', 'assets/Level2/vTile.png');
		    this.load.image('star', 'assets/Level2/diamond.png');
		    
		    this.load.image('coin1', 'assets/Level2/coin1.png');
		    this.load.image('coin2', 'assets/Level2/coin2.png');
		    this.load.image('coin3', 'assets/Level2/coin3.png');
		    
		    this.load.spritesheet('dude', 'assets/Level2/dude.png', 32, 48);
		    this.load.spritesheet('baddie2', 'assets/Level2/baddie.png', 33, 34, 10);
		    this.load.spritesheet('baddie', 'assets/Level2/baddie.png', 33, 34, 10);
		    this.load.spritesheet('boss', 'assets/Level2/boss.png', 57, 54);
		    this.load.spritesheet('door', 'assets/Level2/door.png', 65, 97);
		    
  },		
		
  create: function() {
  	//set world dimensions
    this.game.world.setBounds(0, 0, 3000, 600);
    
    //background
    this.game.add.tileSprite(0, 0, 3000, 600, 'background')
    
    this.game.physics.startSystem(Phaser.Physics.ARCADE);
    
    
    this.platforms = this.game.add.group();
    this.platforms.enableBody = true;
    
    this.ground = this.platforms.create(0, this.game.world.height - 64, 'ground');
    this.ground.body.immovable = true;
    
    this.ledge = this.platforms.create(1000, 450, 'tile');
    this.ledge.body.immovable = true;
    this.ledgeImg = this.game.add.image(1000, 430, 'tileBack');
    this.ledge.body.immovable = true;
    
    this.ledge = this.platforms.create(400, 450, 'tile');
    this.ledge.body.immovable = true;
    this.ledgeImg = this.game.add.image(400, 430, 'tileBack');
    this.ledge.body.immovable = true;
    
    this.ledge = this.platforms.create(630, 350, 'tile');
    this.ledge.body.immovable = true;
    this.ledgeImg = this.game.add.image(630, 330, 'tileBack');
    this.ledge.body.immovable = true;
    
    this.ledge = this.platforms.create(400, 250, 'tile');
    this.ledge.body.immovable = true;
    this.ledgeImg = this.game.add.image(400, 230, 'tileBack');
    this.ledge.body.immovable = true;
    
    this.ledge = this.platforms.create(100, 250, 'tile');
    this.ledge.body.immovable = true;
    this.ledgeImg = this.game.add.image(100, 230, 'tileBack');
    this.ledge.body.immovable = true;
    
    this.ledge = this.platforms.create(400, 150, 'tile');
    this.ledge.body.immovable = true;
    this.ledgeImg = this.game.add.image(400, 130, 'tileBack');
    this.ledge.body.immovable = true;
    
    this.ledge = this.platforms.create(1690, 350, 'tile');
    this.ledge.body.immovable = true;
    this.ledgeImg = this.game.add.image(1690, 330, 'tileBack');
    this.ledge.body.immovable = true;
    
    this.ledge = this.platforms.create(1290, 350, 'tile');
    this.ledge.body.immovable = true;
    this.ledgeImg = this.game.add.image(1290, 330, 'tileBack');
    this.ledge.body.immovable = true;
    
    this.ledge = this.platforms.create(1950, -10, 'vTile');
    this.ledge.body.immovable = true;
    this.ledge.scale.setTo(1.3, 1.3);
    
    this.ledge = this.platforms.create(2200, 450, 'tile');
    this.ledge.body.immovable = true;
    this.ledgeImg = this.game.add.image(2200, 430, 'tileBack');
    this.ledge.body.immovable = true;
    
    this.ledge = this.platforms.create(2500, 350, 'tile');
    this.ledge.body.immovable = true;
    this.ledgeImg = this.game.add.image(2500, 330, 'tileBack');
    this.ledge.body.immovable = true;
    
    door = this.game.add.sprite(1950, 380, 'door');
    door.scale.setTo(1.4, 1.6);
    ifOpen = this.game.add.text(1950, 350, '점수 필요(400점)', { fontSize: '20px', fill: '#FFF' });
    this.game.physics.arcade.enable(door);
    door.body.collideWorldBounds = true;
    door.body.immovable = true;
    //create player
    this.player = this.game.add.sprite(50, this.game.world.height - 150, 'dude');
//    boss = this.game.add.sprite(2900, 300, 'boss');
    
    ghost[0]={'x':120,'y':150,'moving':200};
    ghost[1]={'x':400,'y':50,'moving':200};
    ghost[2]={'x':1800,'y':250,'moving':200};
    ghost[3]={'x':1800,'y':450,'moving':200};
    ghost[4]={'x':1200,'y':400,'moving':200};
    
    for(var i=0; i<5; i++){
    	ghosts[i] = this.game.add.sprite(ghost[i].x, ghost[i].y, 'boss');
    	this.game.physics.arcade.enable(ghosts[i]);
    	ghosts[i].body.bounce.y = 0;
    	ghosts[i].body.gravity.y = 0;
    	ghosts[i].body.collideWorldBounds = true;
    	
    	ghosts[i].animations.add('bossLeft', [5, 6, 7], 5, true);
    	ghosts[i].animations.add('bossRight', [0, 1, 2], 5, true);
    	ghosts[i].animations.add('bossStopleft', [4], 5, true);
    	ghosts[i].animations.add('bossStopright', [3], 5, true);
    	
    }
    
    
    
    baddie[0]={'x':120,'mx':670,'y':150,'moving':100};
    baddie[1]={'x':400,'mx':670,'y':50,'moving':100};
    baddie[2]={'x':1000,'mx':1200,'y':350,'moving':100};
    baddie[3]={'x':1280,'mx':1550,'y':250,'moving':100};
    baddie[4]={'x':400,'mx':670,'y':350,'moving':100};
    baddie[5]={'x':620,'mx':910,'y':250,'moving':100};
    
    
    
    for(var i=0; i < 6 ; i++){
    	baddies[i] = this.game.add.sprite(baddie[i].x, baddie[i].y, 'baddie');
    	this.game.physics.arcade.enable(baddies[i]);
    	baddies[i].body.bounce.y = 0.1;
    	baddies[i].body.gravity.y = 300;
    	baddies[i].body.collideWorldBounds = true;
        
    	baddies[i].animations.add('baddieLeft', [0, 1], 5, true);
    	baddies[i].animations.add('baddieRight', [2, 3], 5, true);
    	baddies[i].animations.add('baddieLeftDead', [4, 5, 6], 5, false);
    	baddies[i].animations.add('baddieRightDead', [9, 8, 7], 5, false);
    }
    
    this.game.physics.arcade.enable(this.player);
//    this.game.physics.arcade.enable(boss);
    
    this.player.body.bounce.y = 0.1;
    this.player.body.gravity.y = 1000;
    this.player.body.collideWorldBounds = true;
    this.player.scale.setTo(1.5,1.5);
    
    
    
    
    
//    boss.body.bounce.y = 0;
//    boss.body.gravity.y = 0;
//    boss.body.collideWorldBounds = true;
    
    
    door.animations.add('open', [0,1,2,3,4,5], 5, false);
    
    
    this.player.animations.add('left', [0, 1, 2, 3], 5, true);
    this.player.animations.add('right', [5, 6, 7, 8], 5, true);
    
    
//    boss.animations.add('bossLeft', [5, 6, 7], 5, true);
//    boss.animations.add('bossRight', [0, 1, 2], 5, true);
//    boss.animations.add('bossStopleft', [4], 5, true);
//    boss.animations.add('bossStopright', [3], 5, true);
    
    
    this.stars = this.game.add.group();
    this.stars.enableBody = true;
    for (var i = 1; i < 20; i++)
    {
        //  Create a star inside of the 'stars' group
        var star = this.stars.create(i * 100, 0, 'star');

        //  Let gravity do its thing
        star.body.gravity.y = 300;

        //  This just gives each star a slightly random bounce value
        star.body.bounce.y = 0.1;
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
    
    
    
    
    
    
    //player initial score of zero
    this.score = score;
    this.playerScore = this.game.add.text(16, 16, 'Score: '+this.score, { fontSize: '32px', fill: '#FFF' });
    this.playerScore.fixedToCamera = true;
    this.playerScore.cameraOffset.setTo(16, 16);
    
    //enable player physics
    this.player.body.collideWorldBounds = true;

    //the camera will follow the player in the world
    this.game.camera.follow(this.player);

    
    this.cursors = this.game.input.keyboard.createCursorKeys();
//    this.cursors2 = this.game.input.keyboard.addKey(Phaser.Keyboard.SPACEBAR);
    
  },
  update: function() {
	this.game.physics.arcade.collide(this.player, this.platforms);
	this.game.physics.arcade.collide(this.stars, this.platforms);
	this.game.physics.arcade.collide(this.player, ghosts);
	this.game.physics.arcade.collide(ghosts, this.platforms);
	
	for(var i=0; i<5; i++){
		for(var j=0; j<5; j++){
			this.game.physics.arcade.collide(ghosts[i], ghosts[j]);
		}
	}
	
	
	this.game.physics.arcade.collide(this.player, doorClose);
	this.game.physics.arcade.collide(baddies, this.platforms);
	this.game.physics.arcade.collide(this.player, baddies, this.dead, null, this);
	
	this.game.physics.arcade.collide(this.player, door, this.nextdoor, null, this);
//	this.game.physics.arcade.overlap(this.player, door, this.nextdoor, null, this);
	this.game.physics.arcade.overlap(this.player, this.stars, this.collectStar, null, this);
	

	
	this.player.body.velocity.x = 0;
	
	
	for(var i=0; i<5; i++){
//		ghosts[i].body.velocity.x =0;
		
		if(this.player.position.y <  ghosts[i].position.y-5){
//			 ghosts[i].body.velocity.x =  ghost[i].moving;
			 ghosts[i].body.velocity.y = -100;
		}else if(this.player.position.y > ghosts[i].position.y){
//			 ghosts[i].body.velocity.x =  ghost[i].moving;
			 ghosts[i].body.velocity.y = 100;
		}
		ghosts[i].body.velocity.x =  ghost[i].moving;
	}
	
	
	if (this.cursors.left.isDown){
		
		for(var i=0; i<5; i++){
			if(this.player.position.x < ghosts[i].position.x){
//				ghost[i].moving = -200;
				ghosts[i].animations.play('bossLeft');
			}else{
				ghost[i].moving = 0;
				ghosts[i].body.velocity.y = 0;
				ghosts[i].animations.play('bossStopright');
			}
		}
		
		
		this.player.body.velocity.x = -200;
		this.player.animations.play('left');
    }else if (this.cursors.right.isDown){
    	
    	
    	for(var i=0; i<5; i++){
	    	if(this.player.position.x > ghosts[i].position.x){
//				ghost[i].moving = 200;
		    	ghosts[i].animations.play('bossRight');
			}else{
				ghost[i].moving = 0;
				ghosts[i].body.velocity.y = 0;
				ghosts[i].animations.play('bossStopleft');
			}
    	}
    	
    	this.player.body.velocity.x = 200;
    	this.player.animations.play('right');
    }else if(this.cursors.down.isDown){
    	
    	
    	
    }else{
    	this.player.animations.stop();
    	this.player.frame = 4;
    	
    	for(var i=0; i<5; i++){
    		ghost[i].moving=0;
	    	if(this.player.position.x < ghosts[i].position.x){
				ghost[i].moving = -200;
				ghosts[i].animations.play('bossLeft');
			}else{
				ghost[i].moving = 200;
		    	ghosts[i].animations.play('bossRight');
			}
    	}
    	
    }
	
//	if (this.cursors2.isDown && this.player.body.touching.down){
//		this.player.body.velocity.y = -500;
//    }
	
	if (this.cursors.up.isDown && this.player.body.touching.down)
    {
    	this.player.body.velocity.y = -500;
    }
	
	
	for(var i=0; i<6; i++){
		if(baddies[i].position.x >= baddie[i].mx ){
			baddie[i].moving=-100;
			baddies[i].animations.play('baddieLeft');
		}else if(baddies[i].position.x <= baddie[i].x){
			baddie[i].moving=100;
			baddies[i].animations.play('baddieRight');
		}
	}
	
	for(var i=0; i<6; i++){
		baddies[i].body.velocity.x = baddie[i].moving;
	}
	
  },
  
  collectStar: function(player, star) {
	  star.kill();
	  this.score += 10;
	  this.playerScore.text = 'Score: ' + this.score;
	  
  },
  
  nextdoor: function(player, door){
	  if(this.score >= 400){
		  door.animations.play('open');
		  door.body.checkCollision.left=false;
		  door.body.checkCollision.right=false;
		  for(var i=0; i<5; i++){
			  ghost[i].moving = 0;
			  ghosts[i].body.velocity.y=0;
			  ghosts[i].body.velocity.x=0;
			  var killGhost = this.game.add.tween(ghosts[i]).to( { alpha: 0 }, 300, Phaser.Easing.Linear.None, true, 0, 3, false);
			  killGhost.onComplete.add(GhostDead,this);
		  }
		  ifOpen.text = '';
		  function GhostDead(ghost){
			  ghost.kill();
			  stageClear = this.game.add.text(2350, 100, 'Stage Clear', { fontSize: '70px', fill: '#000' });
			  this.game.add.tween(stageClear).to( { alpha: 0 }, 500, Phaser.Easing.Linear.None, true, 0, 1000, false);
			  
			  for(var i=0; i<10; i++){
				  var randomX1 = Math.round(Math.random()*800+2100);
				  var randomX2 = Math.round(Math.random()*800+2100);
				  var randomX3 = Math.round(Math.random()*800+2100);
			        finalCoin[0]=this.game.add.image(randomX1, -100, 'coin1');
					finalCoin[1]=this.game.add.image(randomX2, -100, 'coin2');
					finalCoin[2]=this.game.add.image(randomX3, -100, 'coin3');
					
			        var speed = Math.round(Math.random()*1000+2000);
			        
			        this.game.add.tween(finalCoin[0]).to({ y: +500 }, speed, Phaser.Easing.Sinusoidal.InOut, true, 0, 100, false);
			        this.game.add.tween(finalCoin[1]).to({ y: +500 }, speed, Phaser.Easing.Sinusoidal.InOut, true, 0, 100, false);
			        this.game.add.tween(finalCoin[2]).to({ y: +500 }, speed, Phaser.Easing.Sinusoidal.InOut, true, 0, 100, false);
			  }
			  
			  
			  
			  
			  doorClose = this.game.add.sprite(1950, 380, 'door');
			  doorClose.animations.add('close', [5,4,3,2,1,0], 5, false);
			  doorClose.scale.setTo(1.4, 1.6);
			  this.game.physics.arcade.enable(doorClose);
			  doorClose.body.collideWorldBounds = true;
			  doorClose.body.immovable = true;
			  doorClose.animations.play('close');
			  
			  
		  }
		  
	  }else{
		  ifOpen.text = '점수 부족('+(400-this.score)+')';
	  }
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
			
			  if(spareLife==0){
				  this.game.time.events.add(1500, this.gameOver, this);
				  console.log('spareLife0 : '+spareLife);
			  }else{
				  //목숨 줄이기
		    	  spareLife -= 1;
				  var n = lives.length;
				  console.log('미니미 줄어들기 전'+n);
				  console.log('spareLife : '+spareLife);
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
  reload: function(){
	  var params = {'score':this.score, 'spareLife':spareLife};
	  this.game.state.start('Level2', true, false, params);
	  
  },
  
  gameOver: function() {    
    //pass it the score as a parameter 
    this.game.state.start('GameOver', true, false, score);
  },
//  render : function(){
//		this.game.debug.bodyInfo(this.player, 32, 70);
////		this.game.debug.body(this.tigger);
//  }
};

/*
TODO

-audio
-asteriod bounch
*/
