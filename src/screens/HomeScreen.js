import { useQuery } from "@tanstack/react-query";
import { StatusBar } from "expo-status-bar";
import { useColorScheme } from "nativewind";
import React from "react";
import { View, ScrollView, Text } from "react-native";
import { heightPercentageToDP as hp } from "react-native-responsive-screen";
import { SafeAreaView } from "react-native-safe-area-context";

import { fetchBreakingNews, fetchRecommendedNews } from "../../utils/NewsApi";
import BreakingNews from "../components/BreakingNews";
import Header from "../components/Header/Header";
import MiniHeader from "../components/Header/MiniHeader";
import Loading from "../components/Loading/Loading";
import NewsSection from "../components/NewsSection/NewsSection";

export default function HomeScreen() {
  const { colorScheme, toggleColorScheme } = useColorScheme();

  // Breaking News
  const { data, isLoading: isBreakingLoading } = useQuery({
    queryKey: ["breakingNewss"],
    queryFn: fetchBreakingNews,
  });

  // Recommended News
  const { data: recommendedNew, isLoading: isRecommendedLoading } = useQuery({
    queryKey: ["recommededNewss"],
    queryFn: fetchRecommendedNews,
  });

  return (
    <SafeAreaView className=" flex-1 bg-white dark:bg-neutral-900">
      <StatusBar style={colorScheme === "dark" ? "light" : "dark"} />

      <View>
        {/* Header */}
        <Header />

        {/* Breaking News */}
        {isBreakingLoading ? (
          <Loading />
        ) : (
          <View className="">
            <MiniHeader label="Breaking News" />
            <BreakingNews label="Breaking News" data={data.articles} />
          </View>
        )}

        {/* Recommended News */}
        <View>
          <MiniHeader label="Recommended" />
          <ScrollView
            contentContainerStyle={{
              paddingBottom: hp(80),
            }}
          >
            {isRecommendedLoading ? (
              <Loading />
            ) : (
              <NewsSection
                label="Recommendation"
                newsProps={recommendedNew.articles}
              />
            )}
          </ScrollView>
        </View>
      </View>
    </SafeAreaView>
  );
}
