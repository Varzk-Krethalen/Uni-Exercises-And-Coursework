/*
 * Deal with TMGUI interactions from user
 */
public class Controller
{
  private ModelOfTM model;
  private ViewOfTM  view;

  public Controller( ModelOfTM aModelOfTM, ViewOfTM aViewOfTM )
  {
    model = aModelOfTM;
    view  = aViewOfTM;
    view.setController( this );
  }

  public void process( String buttonName )
  {
    model.process( buttonName );
  }

}
