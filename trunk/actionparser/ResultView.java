/**
  * This file is part of VoteBox.
  * 
  * VoteBox is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  * 
  * VoteBox is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  * 
  * You should have received a copy of the GNU General Public License
  * along with VoteBox.  If not, see <http://www.gnu.org/licenses/>.
 */
package actionparser;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tap.BallotImageHelper;

/**
 * View for BallotResultParser.
 * 
 * @author Montrose
 */
public class ResultView extends JFrame {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new ResultView
	 * 
	 * @param results - List of RaceResults to display
	 * @param ballotFile - ballot file to pull images out of
	 */
	public ResultView(List<RaceResult> results, File ballotFile){
		super("Results");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Map<String, Image> titleMap = BallotImageHelper.loadBallotTitles(ballotFile);
		Map<String, Image> raceMap = loadBallotRaces(ballotFile.getAbsolutePath(), null);
		
		JPanel center = new JPanel();
		
		BoxLayout layout = new BoxLayout(center, BoxLayout.Y_AXIS);
		center.setLayout(layout);
		
		for(RaceResult res : results){
			Image title = null;
			
			if(titleMap != null && res.get_res().size() > 0)
				title = titleMap.get(res.get_res().get(0));
			
			if(title != null){
				JPanel titlePanel = new JPanel();
				BoxLayout t = new BoxLayout(titlePanel, BoxLayout.X_AXIS);
				titlePanel.setLayout(t);
				titlePanel.add(new JLabel(new ImageIcon(title)));
				titlePanel.add(Box.createHorizontalGlue());
				
				center.add(titlePanel);
			}//if
			
			for(String resStr : res.get_res()){
				JPanel indented = new JPanel();
				BoxLayout l = new BoxLayout(indented, BoxLayout.X_AXIS);
				indented.setLayout(l);

				if(title != null)
					indented.add(Box.createHorizontalStrut(30));

				indented.add(new JLabel(new ImageIcon(raceMap.get(resStr))));
				indented.add(Box.createHorizontalGlue());

				center.add(indented);
			}
		}//for
		
		center.add(Box.createVerticalGlue());
		
		add(new JScrollPane(center));
		
		setSize(1024, 768);
	}
	
	/**
	 * Taking in a ballot location, tries to load all relavent images into a map of race-ids to Images.
	 * 
	 * @param ballot - The ballot file to read
	 * @param languages - The list of languages in the ballot file
	 * @return a map of race-ids to images, or null if an error was encountered.
	 */
	private static Map<String, Image> loadBallotRaces(String ballot, List<String> languages) {
		try {
			Map<String, Image> racesToImageMap = new HashMap<String, Image>();
			
			ZipFile file = new ZipFile(ballot);
			
			Enumeration<? extends ZipEntry> entries = file.entries();
			
			while(entries.hasMoreElements()){
				ZipEntry entry = entries.nextElement();
				
				if(isRaceImage(entry.getName(), languages)){
					racesToImageMap.put(getRace(entry.getName()), ImageIO.read(file.getInputStream(entry)));
				}//if
			}//while
			
			return racesToImageMap;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param entryName - the Zip entry to consider
	 * @return true if entryName is in the form "media_B*_selected_*.png", ie if it is a "race image"
	 */
	private static boolean isRaceImage(String entryName, List<String> langs){
		if(!entryName.startsWith("media/B"))
			return false;
		
		if(!entryName.endsWith(".png"))
			return false;
		
		if(entryName.indexOf("_selected_") == -1)
			return false;
		if(langs != null)
			if(entryName.indexOf(langs.get(0)) == -1) //grab the first language for now
				return false;
		
		return true;
	}//isRaceImage
	
	/**
	 * Extracts a race-id from a zip entry of a race image.
	 * 
	 * @param name - the entry of the race image.
	 * @return A string in the form B*, that is a valid race id
	 */
	private static String getRace(String name) {
		int start = name.indexOf('B');
		int end = name.indexOf('_');
		
		return name.substring(start, end);
	}
}