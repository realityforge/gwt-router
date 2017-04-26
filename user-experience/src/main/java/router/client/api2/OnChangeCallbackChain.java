package router.client.api2;

import java.util.List;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class OnChangeCallbackChain
{
  @Nonnull
  private final List<RouteEntry<OnChangeCallbackAsync>> _chain;

  public OnChangeCallbackChain( @Nonnull final List<RouteEntry<OnChangeCallbackAsync>> chain )
  {
    _chain = Objects.requireNonNull( chain );
  }

  public void onChange( @Nullable final String previousLocation,
                        @Nonnull final Runnable abortAction,
                        @Nonnull final Runnable nextAction )
  {
    onChange( previousLocation, abortAction, nextAction, 0 );
  }

  private void onChange( @Nullable final String previousLocation,
                         @Nonnull final Runnable abortAction,
                         @Nonnull final Runnable nextAction,
                         final int index )
  {
    if ( index >= _chain.size() )
    {
      nextAction.run();
    }
    else
    {
      final RouteEntry<OnChangeCallbackAsync> entry = _chain.get( index );
      entry.getCallback().onChange( previousLocation, entry.getLocation(), new OnChangeControl()
      {
        @Override
        public void abort()
        {
          abortAction.run();
        }

        @Override
        public void halt()
        {
          nextAction.run();
        }

        @Override
        public void proceed()
        {
          onChange( previousLocation, abortAction, nextAction, index + 1 );
        }
      } );
    }
  }
}
