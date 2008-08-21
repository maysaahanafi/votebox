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
package votebox.auditoriumverifierplugins;

import auditorium.*;
import sexpression.*;
import verifier.*;
import verifier.ast.*;
import verifier.value.*;

public class SignatureVerify extends AST {

	public static final ASTFactory FACTORY = new PrimFactory(2,
			new IConstructor() {

				public AST make(ASExpression from, AST... args) {
					return new SignatureVerify(from, args[0], args[1]);
				}
			}) {

		@Override
		public String getName() {
			return "signature-verify";
		}
	};

	private final AST _cert;
	private final AST _signature;

	private SignatureVerify(ASExpression from, AST cert, AST signature) {
		super(from);
		_cert = cert;
		_signature = signature;
	}

	@Override
	public Value eval(ActivationRecord environment) {
		final Value cert = _cert.eval(environment);
		final Value sig = _signature.eval(environment);

		return cert.execute(new AValueVisitor() {

			@Override
			public Value forExpression(final Expression certvalue) {
				return sig.execute(new AValueVisitor() {

					@Override
					public Value forExpression(Expression sigvalue) {
						try {
							Cert c = new Cert(certvalue.getASE());
							Signature s = new Signature(sigvalue.getASE());
							RSACrypto.SINGLETON.verify(s, c);
							return True.SINGLETON;
						} catch (IncorrectFormatException e) {
							return False.SINGLETON;
						} catch (AuditoriumCryptoException e) {
							return False.SINGLETON;
						}
					}
				});
			}
		});
	}
}
