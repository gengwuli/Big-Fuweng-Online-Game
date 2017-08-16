package gl22_hl57.game_client.view;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import gl22_hl57.game_client.model.Avatar;

public class AvatarRender<TAvatarListItem> extends JLabel implements ListCellRenderer<TAvatarListItem> {

	private static final long serialVersionUID = 5749546683306857335L;

	@Override
	public Component getListCellRendererComponent(JList<? extends TAvatarListItem> list, TAvatarListItem value,
			int index, boolean isSelected, boolean cellHasFocus) {
		this.setText(value.toString());
		this.setForeground(((Avatar)value).getTeamColor());
		this.setOpaque(true);
		return this;
	}

}
