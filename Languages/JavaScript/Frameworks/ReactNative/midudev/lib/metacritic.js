export async function getLatestGames() {
  const LATEST_GAMES =
    "https://cors-anywhere.herokuapp.com/https://www.freetogame.com/api/games";

  const rawData = await fetch(LATEST_GAMES, {
    headers: {
      origin: "*",
      "x-requested-with": "HTTPClient",
    },
  });
  const games = await rawData.json();

  return games.map((item) => {
    const { short_description, id, release_date, thumbnail, title } = item;

    return {
      description: short_description,
      releaseDate: release_date,
      image: thumbnail,
      score: Math.floor(Math.random() * 100) + 1,
      slug: id,
      title,
    };
  });
}
