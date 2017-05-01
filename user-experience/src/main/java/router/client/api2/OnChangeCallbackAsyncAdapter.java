package router.client.api2;

import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import router.client.location.LocationMatch;

final class OnChangeCallbackAsyncAdapter
  implements OnChangeCallbackAsync
{
  private final OnChangeCallback _callback;

  OnChangeCallbackAsyncAdapter( @Nonnull final OnChangeCallback callback )
  {
    _callback = Objects.requireNonNull( callback );
  }

  @Override
  public void onChange( @Nullable String previousLocation,
                        @Nonnull LocationMatch match,
                        @Nonnull final OnChangeControl control )
  {
    if ( _callback.onChange( previousLocation, match ) )
    {
      control.proceed();
    }
    else
    {
      control.abort();
    }
  }
}
