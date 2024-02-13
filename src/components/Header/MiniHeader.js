import React from "react";
import { View, Text } from "react-native";

export default function MiniHeader({ label }) {
  return (
    <View className="px-4 my-4 justify-between flex-row items-center">
      <Text
        className="text-xl text-[#180E19] dark:text-white "
        style={{
          fontFamily: "SpaceGroteskBold",
        }}
      >
        {label}
      </Text>
    </View>
  );
}
