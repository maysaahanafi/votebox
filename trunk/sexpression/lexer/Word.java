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
package sexpression.lexer;

public class Word extends Token {
    private String _word;

    public Word(String word) {
        _word = word;
    }

    public String getWord() {
        return _word;
    }

    @Override
    public String toString() {
        return _word;
    }

    @Override
    public Object execute(ITokenVisitor v) {
        return v.forWord( this );
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Word))
            return false;

        return ((Word) o)._word.equals( _word );
    }
}
