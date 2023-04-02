package si2023.sergiogarcia1alu.p00;

import java.util.ArrayList;

import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types.ACTIONS;
import tools.ElapsedCpuTimer;
import tools.Vector2d;

// Movimientos y mapa cortesia de Adrian Rodriguez Rodriguez

public class AgenteSuperInteligente extends AbstractPlayer
{
	ArrayList<ACTIONS> acciones;
	int contador = 0;
	public AgenteSuperInteligente(StateObservation stateObs, ElapsedCpuTimer elapsedTimer)
	{
		acciones = new ArrayList<>();
		//Config1();
		
		Vector2d pos = stateObs.getAvatarPosition();
		
		switch ((int)pos.y)
		{
		case 450:
			Config0();
			break;
		case 250:
			Config1();
			break;
		case 600:
			Config2();
			break;
		case 150:
			Config3();
			break;
		case 50:
			Config4();
			break;
		}
		
	}
	
	@Override
	public ACTIONS act(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {
		return acciones.get(contador++);
		//return ACTIONS.ACTION_NIL;
	}
	
	private void Config0()
	{
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
		
	}
	
	private void Config1()
	{
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
	}

	private void Config2()
	{
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
	}
	
	private void Config3()
	{
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
	}
	
	private void Config4()
	{
		acciones.add(ACTIONS.ACTION_NIL);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_UP);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_RIGHT);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_LEFT);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
		acciones.add(ACTIONS.ACTION_DOWN);
	}

}
