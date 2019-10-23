#
# Be sure to run `pod lib lint KeanuCore.podspec' to ensure this is a
# valid spec before submitting.
#
# Any lines starting with a # are optional, but their use is encouraged
# To learn more about a Podspec see https://guides.cocoapods.org/syntax/podspec.html
#

Pod::Spec.new do |s|
  s.name             = 'KeanuCore'
  s.version          = '0.1.0'
  s.summary          = 'Keanu is a native iOS Swift implementation of a Matrix client.'

# This description is used to generate tags and improve search results.
#   * Think: What does it do? Why did you write it? What is the focus?
#   * Try to keep it short, snappy and to the point.
#   * Write the description between the DESC delimiters below.
#   * Finally, don't worry about the indent, CocoaPods strips it!

  s.description      = <<-DESC
Keanu is a native iOS Swift implementation of a Matrix client.

It is provided as multiple CocoaPods libraries, so others can easily spin off of
it without the need to fork it. That way, spin-offs can easily stay up to date.
                       DESC

  s.homepage         = 'https://gitlab.com/keanuapp/keanuapp-ios.git'
  # s.screenshots     = 'www.example.com/screenshots_1', 'www.example.com/screenshots_2'
  s.license          = { :type => 'Apache License, Version 2.0', :file => 'LICENSE' }
  s.author           = { 'Guardian Project' => 'support@guardianproject.info' }
  s.source           = { :git => 'https://gitlab.com/keanuapp/keanuapp-ios.git', :tag => s.version.to_s }
  s.social_media_url = 'https://twitter.com/guardianproject'

  s.swift_version = '5.0'

  s.ios.deployment_target = '9.3'

  s.source_files = 'KeanuCore/Classes/**/*'

  s.resources = [
    'KeanuCore/Assets/**/*.storyboard',
    'KeanuCore/Assets/**/*.xib',
    'KeanuCore/Assets/**/*.ttf',
    'KeanuCore/Assets/**/*.xcassets',
    'KeanuCore/Assets/*.lproj/*.*',
  ]

  s.dependency 'MatrixSDK/SwiftSupport'
  s.dependency 'MatrixKit/AppExtension', '~> 0.9'
  s.dependency 'CrossroadRegex', '~> 1.1'
  s.dependency 'Localize', '~> 2.2'
  s.dependency 'FontBlaster', '~> 4.1'

end
