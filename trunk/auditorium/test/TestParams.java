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
package auditorium.test;

import auditorium.IAuditoriumParams;

/**
 * Implementation of IAuditoriumParams for use in test cases. The log is
 * saved in <tt>/tmp</tt>.
 * 
 * @see votebox.AuditoriumParams for the concrete implementation used in live
 *      VoteBox.
 * @author dsandler
 * 
 */
public class TestParams implements IAuditoriumParams {

    public static final TestParams Singleton = new TestParams();

    /*
     * Values originally taken from AuditoriumParams.java to be representative
     * of VoteBox.
     */
    public static final int DISCOVER_TIMEOUT = 4000;
    public static int DISCOVER_PORT = 9782;
    public static final int DISCOVER_REPLY_TIMEOUT = 1000;
    public static final int DISCOVER_REPLY_PORT = 9783;
    public static final int LISTEN_PORT = 9700;
    public static final int JOIN_TIMEOUT = 1000;
    public static final String BROADCAST_ADDRESS = "255.255.255.255";
    public static final String LOG_LOCATION = "/local/derrley/log.out";
    public static final String KEYS_DIRECTORY = "/keys/";
    public static final String RULE_FILE = "rules";
    public static final boolean ENCRYPTION_ENABLED = false;
    public static final boolean USE_COMMIT_CHALLENGE_MODEL = false;
    public static final boolean USE_ELO_TOUCH_SCREEN = false;
	public static final String ELO_TOUCH_SCREEN_DEVICE = null;
	public static final int VIEW_RESTART_TIMEOUT = 5000;
	public static final int DEFAULT_SERIAL_NUMBER = -1;
	public static final String DEFAULT_REPORT_ADDRESS = "";
    public static final int DEFAULT_CHALLENGE_PORT = -1;
    public static final int DEFAULT_HTTP_PORT = 80;
    public static final String DEFAULT_BALLOT_FILE = "";

    public String getBroadcastAddress() {
        return BROADCAST_ADDRESS;
    }

    public int getDiscoverPort() {
        return DISCOVER_PORT;
    }

    public int getDiscoverReplyPort() {
        return DISCOVER_REPLY_PORT;
    }

    public int getDiscoverReplyTimeout() {
        return DISCOVER_REPLY_TIMEOUT;
    }

    public int getDiscoverTimeout() {
        return DISCOVER_TIMEOUT;
    }

    public int getJoinTimeout() {
        return JOIN_TIMEOUT;
    }

    public int getListenPort() {
        return LISTEN_PORT;
    }

    public String getLogLocation() {
        return LOG_LOCATION;
    }

    public auditorium.IKeyStore getKeyStore() {
        return new auditorium.SimpleKeyStore(KEYS_DIRECTORY);
    }

    public String getRuleFile() {
        return RULE_FILE;
    }

	public boolean getCastBallotEncryptionEnabled() {
		return ENCRYPTION_ENABLED;
	}
	
	public boolean getUseCommitChallengeModel(){
		return USE_COMMIT_CHALLENGE_MODEL;
	}

	public boolean getUseEloTouchScreen() {
		return USE_ELO_TOUCH_SCREEN;
	}
	
	public String getEloTouchScreenDevice() {
		return ELO_TOUCH_SCREEN_DEVICE ;
	}
	
	public int getViewRestartTimeout(){
		return VIEW_RESTART_TIMEOUT;
	}
	
	public int getDefaultSerialNumber(){
		return DEFAULT_SERIAL_NUMBER;
	}

	public int getChallengePort() {
		return DEFAULT_CHALLENGE_PORT;
	}

	public String getReportAddress() {
		return DEFAULT_REPORT_ADDRESS;
	}
	
	public int getHttpPort(){
		return DEFAULT_HTTP_PORT;
	}
	
	public String getChallengeBallotFile(){
		return DEFAULT_BALLOT_FILE;
	}
}
