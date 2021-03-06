package router.client.route;

/**
 * This callback is used to perform actual routing.
 */
@FunctionalInterface
public interface RouteCallback
{
  void route( Route2 route, Object element );
}
