package si2023.SergioGarciaMacias.p03.agente89;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import core.game.Observation;
import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types.ACTIONS;
import tools.ElapsedCpuTimer;
import tools.Vector2d;


public interface Cerebro {
	ACTIONS AnalizarMapa();
	ACTIONS Pensar(StateObservation mundo);
}
