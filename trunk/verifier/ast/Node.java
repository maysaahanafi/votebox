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
package verifier.ast;

import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import sexpression.*;
import sexpression.stream.*;
import verifier.*;
import verifier.task.*;
import verifier.value.*;

public class Node extends AST {

	public static int PORT = 9001;
	public static int NUM_THREADS = 2;
	public static final ASTFactory FACTORY = new ASTFactory() {

		@Override
		public String getName() {
			return "node";
		}

		@Override
		public String getPattern() {
			return "(node)";
		}

		@Override
		public AST make(ASExpression from, ListExpression matchresult,
				ASTParser parser) {
			return new Node();
		}
	};

	private Pool _pool;
	private ServerSocket _server;

	private Node() {
		super(null);
	}

	@Override
	public Value eval(ActivationRecord environment) {
		_pool = new Pool(NUM_THREADS);
		_pool.start();
		try {
			_server = new ServerSocket(PORT);
			try {

				Socket s = _server.accept();
				ASEInputStreamReader reader = new ASEInputStreamReader(s
						.getInputStream());
				try {
					while (true) {
						ASExpression ase = reader.read();
						Task t = new Task(ase);
						t.setOutbound(s.getOutputStream());
						_pool.run(t);
					}
				} catch (EOFException e) {}
				System.err.println();

			} catch (SocketException e) {} catch (IOException e) {
				throw new RuntimeException(e);
			} catch (InvalidVerbatimStreamException e) {
				throw new RuntimeException(e);
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		_pool.stop();
		return False.SINGLETON;
	}
}
