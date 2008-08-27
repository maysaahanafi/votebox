/**
  * This file is part of VoteBox.
  * 
  * VoteBox is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License version 3 as published by
  * the Free Software Foundation.
  * 
  * You should have received a copy of the GNU General Public License
  * along with VoteBox, found in the root of any distribution or
  * repository containing all or part of VoteBox.
  * 
  * THIS SOFTWARE IS PROVIDED BY WILLIAM MARSH RICE UNIVERSITY, HOUSTON,
  * TX AND IS PROVIDED 'AS IS' AND WITHOUT ANY EXPRESS, IMPLIED OR
  * STATUTORY WARRANTIES, INCLUDING, BUT NOT LIMITED TO, WARRANTIES OF
  * ACCURACY, COMPLETENESS, AND NONINFRINGEMENT.  THE SOFTWARE USER SHALL
  * INDEMNIFY, DEFEND AND HOLD HARMLESS RICE UNIVERSITY AND ITS FACULTY,
  * STAFF AND STUDENTS FROM ANY AND ALL CLAIMS, ACTIONS, DAMAGES, LOSSES,
  * LIABILITIES, COSTS AND EXPENSES, INCLUDING ATTORNEYS' FEES AND COURT
  * COSTS, DIRECTLY OR INDIRECTLY ARISING OUR OF OR IN CONNECTION WITH
  * ACCESS OR USE OF THE SOFTWARE.
 */

package votebox.middle.driver;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Observer;
import java.util.Stack;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import sexpression.ASExpression;
import votebox.middle.IBallotVars;
import votebox.middle.Properties;
import votebox.middle.ballot.Ballot;
import votebox.middle.ballot.BallotParser;
import votebox.middle.ballot.BallotParserException;
import votebox.middle.ballot.CardException;
import votebox.middle.ballot.IBallotLookupAdapter;
import votebox.middle.ballot.NonCardException;
import votebox.middle.view.IViewFactory;
import votebox.middle.view.ViewManager;

public class Driver {

	private final String _path;

	private final IViewFactory _factory;

	private ViewManager _view;

	private Ballot _ballot;
	
	private boolean _encryptionEnabled;

	private IAdapter _viewAdapter = new IAdapter() {

		public boolean deselect(String uid) throws UnknownUIDException,
				DeselectionException {
			return _view.deselect(uid);
		}

		public Properties getProperties() {
			return _view.getCurrentLayout().getProperties();
		}

		public boolean select(String uid) throws UnknownUIDException,
				SelectionException {
			return _view.select(uid);
		}
	};

	private IAdapter _ballotAdapter = new IAdapter() {

		public boolean deselect(String uid) throws UnknownUIDException,
				DeselectionException {
			return _ballot.deselect(uid);
		}

		public Properties getProperties() {
			return _ballot.getProperties();
		}

		public boolean select(String uid) throws UnknownUIDException,
				SelectionException {
			return _ballot.select(uid);
		}

	};

	private IBallotLookupAdapter _ballotLookupAdapter = new IBallotLookupAdapter() {

		public boolean isCard(String UID) throws UnknownUIDException {
			return _ballot.isCard(UID);
		}

		public String selectedElement(String UID) throws NonCardException,
				UnknownUIDException, CardException {
			return _ballot.selectedElement(UID);
		}

		public boolean exists(String UID) {
			return _ballot.exists(UID);
		}

		public boolean isSelected(String uid) throws UnknownUIDException {
			return _ballot.isSelected(uid);
		}

		public ASExpression getCastBallot() {
			if(!_encryptionEnabled)
				return _ballot.toASExpression();
			
			return _ballot.getCastBallot();
		}

		public int numSelections() {
			return _ballot.getNumSelections();
		}

	};

	public Driver(String path, IViewFactory factory, boolean encryptionEnabled) {
		_path = path;
		_factory = factory;
		_encryptionEnabled = encryptionEnabled;
	}

	public void run(Observer castBallotObserver) {
		IBallotVars vars;
		try {
			vars = new GlobalVarsReader(_path).parse();
		} catch (IOException e) {
			System.err
					.println("The ballot's configuration file could not be found.");
			e.printStackTrace();
			return;
		}
		try {
			_ballot = new BallotParser().getBallot(vars);
		} catch (BallotParserException e) {
			System.err
					.println("The ballot's XML file was unable to be parsed.");
			e.printStackTrace();
			return;
		}
		_ballot.setViewAdapter(_viewAdapter);
		_view = new ViewManager(_ballotAdapter, _ballotLookupAdapter, vars,
				_factory);
		
		if(castBallotObserver != null)
			_view.registerForCastBallot(castBallotObserver);
		
		_view.run();
	}
	
	public void run(){
		run(null);
	}

	public void kill() {
		_view.dispose();
	}
    
    /**
     * Gets this VoteBox instance's view.  Used to allow the caller to register for
     * the cast ballot event in the view manager.
     * @return the view manager
     */
    public ViewManager getView() {
        return _view;
    }

	public static void unzip(String src, String dest) throws IOException {
		if(!(new File(dest)).exists()){
			(new File(dest)).mkdirs();
		}//if
		
		ZipFile zipFile = new ZipFile(src);
		Enumeration<? extends ZipEntry> entries = zipFile.entries();
		byte[] buf = new byte[1024];
		int len;
		
		while (entries.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) entries.nextElement();

			if (entry.isDirectory()) {
				//Create the directory using the proper seperator for this platform
				File newDir = new File(dest, entry.getName().replace('/', File.separatorChar));
				newDir.mkdirs();
				
			} else {
				InputStream in = zipFile.getInputStream(entry);
				
				//Create the file path, using the proper seperator char
				File outFile = new File(dest, entry.getName().replace('/', File.separatorChar));
				
				OutputStream out = new BufferedOutputStream(
						new FileOutputStream(outFile));
				while((len = in.read(buf)) >= 0)
				      out.write(buf, 0, len);
				in.close();
				
				out.flush();
				out.close();
			}
		}

		zipFile.close();
	}
	
	public static void deleteRecursivelyOnExit(String dir) {
		Stack<File> dirStack = new Stack<File>();
        dirStack.add( new File(dir) );
        while (!dirStack.isEmpty()) {
            File file = dirStack.pop();
            file.deleteOnExit();
            File[] children = file.listFiles();
            for (File f : children) {
                if (f.isDirectory())
                    dirStack.add( f );
                else
                    f.deleteOnExit();
            }
            if (dirStack.size() > 100)
                return;
        }
	}
}
