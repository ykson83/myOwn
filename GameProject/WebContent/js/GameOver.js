var SpaceHipster = SpaceHipster || {};

//title screen
SpaceHipster.GameOver = function(){};

SpaceHipster.GameOver.prototype = {
		
  init: function(params){
	  this.score = params;
  },
		
  create: function() {

    //start game text
    var text = "SCORE : "+this.score+"\n다시하기(클릭)";
    var style = { font: "30px Arial",  align: "center" };
    var t = this.game.add.text(this.game.width/2, this.game.height/2, text, style);
    t.anchor.set(0.5);

  },
  update: function() {
    if(this.game.input.activePointer.justPressed()) {
      this.game.state.start('Preload');
//      this.game.state.start('Level2');
    };
  }
};