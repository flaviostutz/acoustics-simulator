# acoustics-simulator
This project is an attempt to create an acoustic simulator so that you can create a 3D space, place a speaker in space, place a mic in space and "listen" to what the microphone would sense

## Why
I made this project while I was at university (Physics - 2005) and, as a musician, I wanted to truly understand the nature of sound traveling closed spaces. At the same time I was upgrading the sound system at my church and wanted to use this tool (I hadn't find this type of tool at the moment).

It was a lot of fun doing this!

## Approach
The sound travels space using a wave model. My approach was to "launch" a bunch of vectors from the sound origin in all directions (according to the speaker spread specs). Those vectors are reflected at all surfaces on the environment. At each reflection, depending on the material, the "sound vector" loses some energy due to material's absorption characteristics and the space it travelled too (due to energy spread over space using a spherical model).

## Accomplishements
The software shows a 3D space (modeled through code) along with a speaker and a mic. The vectors (with a configurable density) is drawn from the speaker to the mic, traveling and reflecting in space.
After calculations, it is shown a "reverberantion time graph" of the environment.

## Findings
The "vector" approach needs too much computation power in order to generate good results, because a poor vector density turn it hard for the "vectors" to reach the mic so we have a poor statistical sampling.

## Next steps (anyone?)
I was thinking about using the great 3D Wave Pool implementation from Paul Falstad (http://www.falstad.com/wavebox/) in order to achieve better results. May be someday...
