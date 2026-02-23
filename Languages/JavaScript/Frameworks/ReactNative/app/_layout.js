import { Stack, Link } from "expo-router";
import { View, Pressable } from "react-native";
import { Logo, CircleInfoIcon } from "../components/Icons";

import "../global.css";

export default function Layout() {
  return (
    <View className="flex-1">
      <Stack
        screenOptions={{
          headerStyle: { backgroundColor: "black" },
          headerTintColor: "white",
          headerTitle: "",
          headerLeft: headerLeft,
          headerRight: headerRight,
        }}
      />
    </View>
  );
}

const headerLeft = () => {
  return (
    <Link asChild href="/">
      <Pressable>
        <Logo />
      </Pressable>
    </Link>
  );
};

const headerRight = () => {
  return (
    <Link asChild href="/about">
      <Pressable>
        <CircleInfoIcon />
      </Pressable>
    </Link>
  );
};
