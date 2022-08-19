package dizoo.std.designpatterns.structure.decorator.grep;

import java.io.BufferedReader;
import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by hx on 16-8-24.
 */
public class GrepReader extends FilterReader {
  protected String subString;
  protected BufferedReader br;
  private int lineNumber;

  /**
   * Creates a new filtered reader.
   *
   * @param in a Reader object providing the underlying stream.
   * @throws NullPointerException if <code>in</code> is <code>null</code>
   */
  protected GrepReader(Reader in) {
    super(in);
  }

  public GrepReader(Reader in, String subString) {
    super(in);
    this.subString = subString;
    this.br = new BufferedReader(in);
    lineNumber = 0;
  }

  public final String readLine() throws IOException {
    String line;
    do {
      line = br.readLine();
      lineNumber++;
    } while (line != null && line.indexOf(subString) == -1);
    return line;
  }

  public int getLineNumber() {
    return lineNumber;
  }
}
