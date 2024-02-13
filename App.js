import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { useFonts } from "expo-font";

import AppNavigation from "./src/navigation";

const queryClient = new QueryClient();

export default function App() {
  const [fontsLoaded, fontError] = useFonts({
    SpaceGroteskSemiBold: require("./src/fonts/SpaceGrotesk-SemiBold.ttf"),
    SpaceGroteskBold: require("./src/fonts/SpaceGrotesk-Bold.ttf"),
    SpaceGroteskMedium: require("./src/fonts/SpaceGrotesk-Medium.ttf"),
  });

  if (!fontsLoaded && !fontError) {
    return null;
  }

  return (
    <QueryClientProvider client={queryClient}>
      <AppNavigation />
    </QueryClientProvider>
  );
}
