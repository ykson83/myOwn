var SpaceHipster = SpaceHipster || {};

SpaceHipster.Boot = function(){};

//setting game configuration and loading the assets for the loading screen
SpaceHipster.Boot.prototype = {
  preload: function() {
	  
  },
  create: function() {
  	//loading screen will have a white background
    this.game.stage.backgroundColor = '#fff';

    //scaling options
	this.scale.scaleMode = Phaser.ScaleManager.RESIZE;
//	this.scale.minWidth = 240;
//	this.scale.minHeight = 170;
//	this.scale.maxWidth = 2880;
//	this.scale.maxHeight = 1920;
	this.scale.minWidth = 240;
	this.scale.minHeight = 170;
	this.scale.maxWidth = 800;
	this.scale.maxHeight = 600;
	
	//have the game centered horizontally
	this.scale.pageAlignHorizontally = true;

	//screen size will be set automatically
	// DEPRECATED this.scale.setScreenSize(true);

	//physics system for movement
	this.game.physics.startSystem(Phaser.Physics.ARCADE);
    
    this.state.start('Preload');
  }
};
