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
package auditorium.loganalysis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import auditorium.*;

/**
 * Given a log file, generate a visual dag of the messages and print a
 * statistics file. The main takes a list of files to generate statistics about --
 * for each file A, A.pdf and A.stat will be generated. The pdf is the graphviz
 * generated visual DAG, and the stat file gives you some interesting
 * statistics.
 * 
 * @author kyle
 * 
 */
public class Graph {

    /**
     * @param args:
     *            this is a list of files to parse and output graphs of.
     */
    public static void main(String[] args) throws Exception {
        for (String s : args)
            graph( s );
    }

    public static void graph(String filename) throws Exception {
        Dag d = new Dag( filename );
        d.build();
        GraphViz gvz = new GraphViz();
        gvz.addln( "digraph gr {" );
        gvz.addln( "nodesep=1.0;" );
        gvz.addln( "mode=\"circuit\";" );
        gvz.addln( "rankdir=\"TB\";" );
        gvz
                .addln( "edge [ arrowsize = .4, fontsize = 11, labeldistance = 1.5, labelfloat=\"false\"];" );

        for (MessagePointer mp : d.getDag().keySet()) {
            String str = "A" + mp.getNodeId() + "_" + mp.getNumber()
                    + " [label=\"" + mp.toString() + "\"]";
            gvz.addln( str );
        }

        for (MessagePointer from : d.getDag().keySet()) {
            for (MessagePointer to : d.getDag().get( from )) {
                String str = "A" + from.getNodeId() + "_" + from.getNumber()
                        + " -> " + "A" + to.getNodeId() + "_" + to.getNumber()
                        + ";";
                gvz.addln( str );
            }
        }
        gvz.addln( "}" );

        gvz.writeGraph( new File( filename ) );
        stats( d, filename );
    }

    public static void stats(Dag dag, String filename) throws Exception {
        PrintWriter writer = new PrintWriter( new FileOutputStream( new File(
                filename + ".stat" ) ) );
        HashMap<Integer, Integer> stats = dag.getBranchStatistics();
        for (Integer i : stats.keySet())
            writer.write( i + ":" + stats.get( i ) + "\n" );
        writer.close();
    }
}
