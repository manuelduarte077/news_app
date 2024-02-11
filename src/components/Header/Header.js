import { useNavigation } from "@react-navigation/native";
import { useColorScheme } from "nativewind";
import React from "react";
import { Switch, Text, View, Image, TouchableOpacity } from "react-native";

export default function Header() {
  const navigation = useNavigation();
  const { colorScheme, toggleColorScheme } = useColorScheme();

  return (
    <View className="flex-row mx-4 mt-4 justify-between">
      <View className="flex-row items-center">
        <Image
          source={
            colorScheme === "dark"
              ? require("../../../assets/icon-header-white.png")
              : require("../../../assets/icon-header.png")
          }
        />

        <Text
          className="text-2xl text-[#180E19] dark:text-white m-3"
          style={{
            fontFamily: "SpaceGroteskBold",
          }}
        >
          Nova Feed
        </Text>
      </View>
      <View className="flex-row space-x-4 rounded-full justify-between">
        <Switch value={colorScheme === "dark"} onChange={toggleColorScheme} />

        {/* <TouchableOpacity
          onPress={() => navigation.navigate("Search")}
          className="bg-gray-200 dark:bg-green-800  rounded-full p-2"
        >
          <MagnifyingGlassIcon
            size={25}
            strokeWidth={2}
            color={colorScheme === "dark" ? "white" : "green"}
          /> 
        </TouchableOpacity> */}
      </View>
    </View>
  );
}
