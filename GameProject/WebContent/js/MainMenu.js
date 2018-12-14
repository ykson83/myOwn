var SpaceHipster = SpaceHipster || {};

//title screen
SpaceHipster.MainMenu = function(){};

SpaceHipster.MainMenu.prototype = {
  create: function() {

    //start game text
    var text = "시작 하기(클릭)";
    var style = { align: "center" };
    var t = this.game.add.text(this.game.width/2, this.game.height/2, text, style);
    t.anchor.set(0.5);

  },
  update: function() {
    if(this.game.input.activePointer.justPressed()) {
      this.game.state.start('Game');
//      this.game.state.start('Level2');
    };
  }
};