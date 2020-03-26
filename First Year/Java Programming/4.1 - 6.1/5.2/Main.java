public class Main
{
  public static void main( String args[] )
  {
    ModelOfTM model = new ModelOfTM();
    ViewOfTM  view  = new ViewOfTM();
                      new Controller( model, view );

    model.addObserver( view );       // Add observer to the model
  }
}
