package gl22_hl57.game_client.model;

import java.awt.Color;
import java.io.Serializable;
import java.util.HashSet;
import java.util.UUID;

public class Team implements Serializable {

	private static final long serialVersionUID = 6077112064389206645L;
	
	public static final Team DEFAULT_TEAM = new Team(Color.BLACK);
	
	private Color teamColor;
	
	private UUID teamId = UUID.randomUUID();
	
	private HashSet<Avatar> teammates = new HashSet<>();

	public Team(Color color) {
		teamColor = color;
	}

	public Color getTeamColor() {
		return teamColor;
	}

	public UUID getTeamId() {
		return teamId;
	}
	
	public boolean addMember(Avatar avatar) {
		return teammates.add(avatar);
	}
	
	public boolean isInTeam(Avatar avatar) {
		return teammates.contains(avatar);
	}
	
	public HashSet<Avatar> getMembers() {
		return teammates;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((teamColor == null) ? 0 : teamColor.hashCode());
		result = prime * result + ((teamId == null) ? 0 : teamId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj==null || !(obj instanceof Team))
			return false;
		return this.hashCode()==obj.hashCode();
	}
}
