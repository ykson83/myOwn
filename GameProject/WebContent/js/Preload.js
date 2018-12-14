var SpaceHipster = SpaceHipster || {};

//loading the game assets
SpaceHipster.Preload = function(){};

SpaceHipster.Preload.prototype = {
  
  preload: function() {

    
  },
  create: function() {
  	this.state.start('MainMenu');
  }
};