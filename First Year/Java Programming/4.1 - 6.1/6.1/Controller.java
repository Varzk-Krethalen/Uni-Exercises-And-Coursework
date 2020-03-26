
/*
 * Deal with DiaryGUI interactions from user
 */
public class Controller
{
  private ModelOfDiary diary;
  private ViewOfDiary  view;

  public Controller( ModelOfDiary aModelOfDiary, ViewOfDiary aViewOfDiary )
  {
    diary = aModelOfDiary;
    view  = aViewOfDiary;
    view.setController( this );
  }

  public void process( String buttonName, String message )
  {
    if ( message != null )
      diary.saveMessageForToday( message );
    diary.changeDate( buttonName );
  }

  public void close()
  {
    diary.close();
  }

}
