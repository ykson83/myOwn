var SpaceHipster = SpaceHipster || {};

SpaceHipster.game = new Phaser.Game(800, 600, Phaser.AUTO, 'test');

SpaceHipster.game.state.add('Boot', SpaceHipster.Boot);
SpaceHipster.game.state.add('Preload', SpaceHipster.Preload);
SpaceHipster.game.state.add('MainMenu', SpaceHipster.MainMenu);
SpaceHipster.game.state.add('Game', SpaceHipster.Game);
SpaceHipster.game.state.add('Level2', SpaceHipster.Level2);
SpaceHipster.game.state.add('GameOver', SpaceHipster.GameOver);

SpaceHipster.game.state.start('Boot');




