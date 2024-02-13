import { useColorScheme } from "nativewind";
import React from "react";
import { View, ActivityIndicator } from "react-native";

export default function Loading() {
  const { colorScheme } = useColorScheme();

  return (
    <View className="flex-1 justify-center items-center">
      <ActivityIndicator
        size="large"
        color={colorScheme === "dark" ? "white" : "black"}
      />
    </View>
  );
}
